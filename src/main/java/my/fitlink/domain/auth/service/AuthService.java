package my.fitlink.domain.auth.service;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.fitlink.domain.auth.Repository.RefreshTokenRepository;
import my.fitlink.domain.auth.dto.TokenResponse;
import my.fitlink.domain.auth.entity.RefreshToken;
import my.fitlink.domain.user.dto.request.UserRequest;
import my.fitlink.domain.user.dto.response.UserResponse;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.repository.UserRepository;
import my.fitlink.global.security.auth.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public User signUp(UserRequest.SignUp request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.toSignUp(request.nickname(),request.email(),encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public UserResponse.Login signIn(UserRequest.Login request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String accessToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        refreshTokenRepository.findByEmail(user.getEmail())
                .ifPresent(token -> token.revokeToken());

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.getEmail()));

        return UserResponse.Login.from(user, accessToken);
    }

    @Transactional
    public TokenResponse.Detail reissue(String accessToken) {
        log.info("AccessToken = {}", accessToken);

        // access토큰 유효성 검증
        if (jwtTokenProvider.validateToken(accessToken)) {
            return TokenResponse.Detail.from(accessToken);
//            throw new IllegalArgumentException("AccessToken이 아직 유효합니다.");
//            예외처리를 하는 것보다는 프론트가 존재하지 않으므로 기존의 accessToken을 제공
        }

        // access 토큰으로부터 Email 추출해서 refreshToken 조회필요.
        String accessTokenEmail;
        try {
            accessTokenEmail = jwtTokenProvider.getUserPk(accessToken);
        } catch (ExpiredJwtException e) {
            accessTokenEmail = e.getClaims().getSubject();
        }

        // DB 리프레쉬 토큰 조회해야함.
        RefreshToken tokenEntity = refreshTokenRepository.findByEmail(accessTokenEmail)
                .orElseThrow(()->new IllegalArgumentException("Invalid email or refresh token"));
        log.info("RefreshToken = {}", tokenEntity.getRefreshToken());
        // 유효기간 검증
        if (jwtTokenProvider.validateToken(tokenEntity.getRefreshToken())) {
            throw new IllegalArgumentException("refresh token expired");
        }

        // accessToken 과 RefreshToken 동일한지 검증
        if (!tokenEntity.getEmail().equals(accessTokenEmail)) {
            throw new IllegalArgumentException("재로그인 or 탈취당함.");
        }

        if (tokenEntity.isRevoked()) {
            throw new IllegalArgumentException("RefreshToken was revoked");
        }

        User user = userRepository.findByEmail(tokenEntity.getEmail())
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));

        /* 토큰 재발급 */
        String newAccessToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());
        log.info("newAccessToken = {}", newAccessToken);
        log.info("newRefreshToken = {}", newRefreshToken);
        /* 기존 리프레쉬 토큰 폐기 */
        tokenEntity.revokeToken();
        refreshTokenRepository.save(new RefreshToken(newRefreshToken, user.getEmail()));
        return TokenResponse.Detail.from(newAccessToken);
    }
}

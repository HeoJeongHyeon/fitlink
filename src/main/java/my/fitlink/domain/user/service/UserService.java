package my.fitlink.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.fitlink.domain.user.dto.request.UserRequest;
import my.fitlink.domain.user.dto.response.UserResponse;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.repository.UserRepository;
import my.fitlink.global.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public User signUp(UserRequest.SignUp request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.toSignUp(request.nickname(),request.email(),encodedPassword);
        return userRepository.save(user);
    }


    public UserResponse.Login login(UserRequest.Login request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());


        return UserResponse.Login.from(user, token);
    }
}

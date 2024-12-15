package my.fitlink.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import my.fitlink.domain.auth.dto.TokenRequest;
import my.fitlink.domain.auth.dto.TokenResponse;
import my.fitlink.domain.auth.service.AuthService;
import my.fitlink.domain.user.dto.request.UserRequest;
import my.fitlink.domain.user.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
@Tag(name = "Auth API ")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "사용자 회원 가입")
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse.Details> signUp(@RequestBody UserRequest.SignUp request) {
        return ResponseEntity.ok().body(
                UserResponse.Details.from(authService.signUp(request))
        );
    }

    @Operation(summary = "사용자 로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request, HttpServletResponse response) {
        return ResponseEntity.ok().body(authService.signIn(request));
    }

    @Operation(summary = "access,refresh 토큰 재발급 ")
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse.Detail> reissue(@RequestBody TokenRequest.Create token) {
        return ResponseEntity.ok()
                .body(authService.reissue(token.accessToken()));
    }

    @Operation(summary = "test")
    @GetMapping("/test")
    public ResponseEntity home() {
        return ResponseEntity.ok().body("ok");
    }

}

package my.fitlink.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.fitlink.domain.user.dto.request.UserRequest;
import my.fitlink.domain.user.dto.response.UserResponse;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.repository.UserRepository;
import my.fitlink.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "사용자 회원 가입")
    @PostMapping()
    public ResponseEntity<UserResponse.Details> signUp(@RequestBody UserRequest.SignUp request) {
        return ResponseEntity.ok().body(
                UserResponse.Details.from(userService.signUp(request))
        );
    }

    @Operation(summary = "사용자 로그인")
    @PostMapping("/login")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request) {
        return ResponseEntity.ok().body(userService.login(request));
    }

}

package my.fitlink.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRequest {

    public record SignUp(
            @NotBlank(message = "닉네임은 필수입니다.")
            String nickname,
            @NotBlank(message = "비밀번호는 필수입니다.")
            @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                    message = "비밀번호는 8자 이상, 문자, 숫자, 특수문자를 포함해야 합니다.")
            String password,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 아닙니다.")
            String email
    ) {
    }

    public record Login(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일형식이 아닙니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {
    }

    public record Update(
            @NotBlank(message = "닉네임 필수입니다.")
            String nickname
    ) {
    }

}
    /* TODO 프로필 수정 정보 */

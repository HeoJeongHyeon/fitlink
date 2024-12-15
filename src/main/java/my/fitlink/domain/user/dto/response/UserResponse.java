package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.user.entity.Role;
import my.fitlink.domain.user.entity.User;

import java.time.LocalDateTime;

public class UserResponse {

    public record Details(
            Long userId,
            String nickname,
            String email,
            Role role
    ){
        public static Details from(User user) {
            return new Details(
                    user.getId(),
                    user.getNickname(),
                    user.getEmail(),
                    user.getRole()
            );
        }
    }

    public record Summary(
            String nickname,
            String email
    ){
        public static Summary from(User user) {
            return new Summary(
                    user.getNickname(),
                    user.getEmail()
            );
        }
    }

    public record Login(
            String email,
            String nickname,
            String accessToken
    ) {
        public static Login from(User user,String accessToken) {
            return new Login(
                    user.getEmail(),
                    user.getNickname(),
                    accessToken
            );
        }
    }
}

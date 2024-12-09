package my.fitlink.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.user.dto.request.UserRequest;
import my.fitlink.global.entity.BaseTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    private User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    /* request 시 사용 DTO -> Entity */
    public static User toUser(UserRequest.SignUp request) {
        return new User(request.nickname(),
                request.email(),
                request.password());
    }


    /* todo 닉네임 수정, 비밀번호 수정*/

}

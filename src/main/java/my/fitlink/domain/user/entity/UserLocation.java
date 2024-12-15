package my.fitlink.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.user.dto.request.UserLocationRequest;
import my.fitlink.global.entity.BaseTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLocation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String dong;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;

    private UserLocation(User user, String dong, double latitude, double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /* request 시 사용 DTO -> Entity */
    public static UserLocation from(UserLocationRequest.create request,User user) {
        return new UserLocation(user, request.dong(), request.latitude(), request.longitude());
    }

    public void updateLocation(Double latitude, Double longitude,String dong) {
        this.dong = dong;
        this.latitude = latitude;
        this.longitude = longitude;
    }



}

package my.fitlink.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.user.dto.request.ProfileRequest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int benchPress;
    @Column(nullable = false)
    private int squat;
    @Column(nullable = false)
    private int deadLift;
    @Column(nullable = false)
    private String introduce;
    @Column(nullable = false)
    private String workoutTime;

    private Profile(int benchPress, int squat, int deadLift, String introduce, String workoutTime,User user) {
        this.benchPress = benchPress;
        this.squat = squat;
        this.deadLift = deadLift;
        this.introduce = introduce;
        this.workoutTime = workoutTime;
        this.user = user;
    }

    /* 여러 매개변수를 받아, 인스턴스 반환. 정적 팩토리 메서드 사용*/
    public static Profile from(ProfileRequest.create request,User user) {
        return new Profile(
                request.benchPress(),
                request.squat(),
                request.deadLift(),
                request.introduce(),
                request.workoutTime(),
                user
        );
    }
}

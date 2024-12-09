package my.fitlink.domain.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProfileRequest {

    public record create(

            @Min(value=0,message = " 무게를 입력해주세요. ")
            int benchPress,
            @Min(value=0,message = " 무게를 입력해주세요. ")
            int squat,
            @Min(value=0,message = " 무게를 입력해주세요. ")
            int deadLift,
            @NotBlank(message = "자기소개는 필수입니다.")
            String introduce,
            @NotBlank(message = "주로 운동가는 시간대를 적어주세요.")
            String workoutTime
    ) {

    }
}

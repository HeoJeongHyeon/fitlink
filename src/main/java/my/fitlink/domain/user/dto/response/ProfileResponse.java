package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.user.entity.Profile;

public class ProfileResponse {

    public record Details(
            Long profileId,
            int benchPress,
            int squat,
            int deadLift,
            String introduce,
            String workoutTime,
            Long userId
    ) {
        public static Details from(Profile profile) {
            return new Details(
                    profile.getId(),
                    profile.getBenchPress(),
                    profile.getSquat(),
                    profile.getDeadLift(),
                    profile.getIntroduce(),
                    profile.getWorkoutTime(),
                    profile.getUser().getId()
            );
        }
    }

    public record Strength(
            int benchPress,
            int squat,
            int deadLift
    ) {
        public static Strength from(Profile profile) {
            return new Strength(
                    profile.getBenchPress(),
                    profile.getSquat(),
                    profile.getDeadLift()
            );
        }
    }

}
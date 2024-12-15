package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.user.entity.WorkoutDay;

public record WorkoutDayResponse (
        String workoutType,
        String description
)
     {
        public static WorkoutDayResponse from(WorkoutDay workoutDay) {
            return new WorkoutDayResponse(
                    workoutDay.getWorkoutType(),
                    workoutDay.getDescription()

            );
        }
    }



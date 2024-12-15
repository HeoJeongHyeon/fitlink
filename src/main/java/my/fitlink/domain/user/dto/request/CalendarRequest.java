package my.fitlink.domain.user.dto.request;

import my.fitlink.domain.user.entity.WorkoutDay;

import java.util.Map;

public class CalendarRequest {

    public record Create(
            int year,
            int month,
            Map<Integer, WorkoutDayRequest> workoutDays
    ) {}

    public record Update(
            int day,
            String workoutType,
            String description
    ) {

    }
}

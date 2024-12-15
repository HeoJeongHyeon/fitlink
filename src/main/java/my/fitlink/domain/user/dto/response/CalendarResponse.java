package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.user.entity.Calendar;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.entity.WorkoutDay;

import java.util.HashMap;
import java.util.Map;

public class CalendarResponse {

    public record CalendarInfo(
            Long userId,
            int year,
            int month,
            Map<Integer, WorkoutDayResponse> workoutDays
    ) {
        public static CalendarInfo from(Calendar calendar) {
            Map<Integer, WorkoutDayResponse> workoutDayResponses = new HashMap<>();
            calendar.getWorkoutDay().forEach((day, workoutDay) ->
                    workoutDayResponses.put(day, WorkoutDayResponse.from(workoutDay)));
            return new CalendarInfo(
                    calendar.getUser().getId(),
                    calendar.getYear(),
                    calendar.getMonth(),
                    workoutDayResponses

            );
        }

    }
}

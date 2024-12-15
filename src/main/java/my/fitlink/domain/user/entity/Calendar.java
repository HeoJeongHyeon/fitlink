package my.fitlink.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.user.dto.request.CalendarRequest;
import my.fitlink.domain.user.dto.request.WorkoutDayRequest;
import my.fitlink.domain.user.dto.response.WorkoutDayResponse;

import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "calendars")
public class Calendar {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",unique = true)
    private User user;

    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int month;

    @ElementCollection
    @CollectionTable(
            name = "calendar_workout",
            joinColumns = @JoinColumn(name = "calendar_id")
    )
    private Map<Integer, WorkoutDay> workoutDay;


    private Calendar(User user, int year, int month, Map<Integer, WorkoutDay> workoutDay) {
        this.user = user;
        this.year = year;
        this.month = month;
        this.workoutDay = workoutDay;
    }

    public static Calendar toCalendar(CalendarRequest.Create request,User user) {
        Map<Integer,WorkoutDay> workoutDays = new HashMap<>();
        request.workoutDays().forEach((day, workoutDayRequest) ->
                workoutDays.put(day, new WorkoutDay(
                        workoutDayRequest.workoutType(), workoutDayRequest.description())
                ));
        return new Calendar(
                user,
                request.year(),
                request.month(),
                workoutDays
        );
    }

}

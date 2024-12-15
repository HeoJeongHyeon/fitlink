package my.fitlink.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkoutDay {

    @Column(nullable = false)
    private String workoutType;

    private String description;

    public WorkoutDay(String workoutType, String description) {
        this.workoutType = workoutType;
        this.description = description;
    }

}
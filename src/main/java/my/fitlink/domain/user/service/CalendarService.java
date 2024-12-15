package my.fitlink.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.fitlink.domain.user.dto.request.CalendarRequest;
import my.fitlink.domain.user.dto.response.CalendarResponse;
import my.fitlink.domain.user.entity.Calendar;
import my.fitlink.domain.user.entity.Profile;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.entity.WorkoutDay;
import my.fitlink.domain.user.repository.CalendarRepository;
import my.fitlink.domain.user.repository.ProfileRepository;
import my.fitlink.global.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarResponse.CalendarInfo getCalendar() {
        User user = SecurityUtil.getCurrentUser();
        Calendar calendar = calendarRepository.findByUserId(user.getId());

        return CalendarResponse.CalendarInfo.from(calendar);
    }

    @Transactional
    public CalendarResponse.CalendarInfo addCalendar(CalendarRequest.Create request) {
        User user = SecurityUtil.getCurrentUser();
        Calendar calendar = Calendar.toCalendar(request, user);
        calendarRepository.save(calendar);

        return CalendarResponse.CalendarInfo.from(calendar);
    }

    @Transactional
    public CalendarResponse.WorkoutDayInfo updateCalendar(CalendarRequest.Update request) {
        User user = SecurityUtil.getCurrentUser();
        Calendar calendar = calendarRepository.findByUserId(user.getId());
        calendar.updateWorkoutDay(request.day(), request.workoutType(), request.description());

        return CalendarResponse.WorkoutDayInfo.from(request);
    }
}

package my.fitlink.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.fitlink.domain.user.dto.request.CalendarRequest;
import my.fitlink.domain.user.dto.response.CalendarResponse;
import my.fitlink.domain.user.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar")
@Tag(name = "Calendar API")
@RequiredArgsConstructor
public class CalendarController {


    private final CalendarService calendarService;

    @PostMapping("/add")
    public ResponseEntity<CalendarResponse.CalendarInfo> addCalendar(@RequestBody CalendarRequest.Create request) {

        return ResponseEntity.ok()
                .body(calendarService.addCalendar(request));
    }


    @GetMapping()
    public ResponseEntity<CalendarResponse.CalendarInfo> getCalendar() {
        return ResponseEntity.ok()
                .body(calendarService.getCalendar());
    }



}

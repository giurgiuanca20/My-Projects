package org.example.spring1.googleCalendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.example.spring1.UrlMapping.*;


@RestController
@RequestMapping(GOOGLE)
@RequiredArgsConstructor
@CrossOrigin
public class GoogleCalendarController {
    private final GoogleCalendarService googleCalendarService; // Adăugat 'final'

    @GetMapping("/events")
    public List<Event> getUpcomingEvents() throws IOException, GeneralSecurityException {
        return googleCalendarService.getUpcomingEvents();
    }
    @PostMapping(ADDGOOGLE)
    public void addEvent(@RequestBody Google google) throws IOException, GeneralSecurityException {
        String startDateTime=google.getHour();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(startDateTime, timeFormatter);
        LocalTime endTime = startTime.plusHours(2);

        LocalDateTime startDateTimeFull = LocalDateTime.of(LocalDate.now(), startTime);
        LocalDateTime endDateTimeFull = LocalDateTime.of(LocalDate.now(), endTime);

        // Convert LocalDateTime to RFC3339 format with timezone
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter rfc3339Formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        String startDateTimeStr = startDateTimeFull.atZone(zoneId).format(rfc3339Formatter);
        String endDateTimeStr = endDateTimeFull.atZone(zoneId).format(rfc3339Formatter);

        DateTime start = new DateTime(startDateTimeStr);
        DateTime end = new DateTime(endDateTimeStr);

         googleCalendarService.addEvent("Work", "Romania", google.getText(), start, end);
    }
}


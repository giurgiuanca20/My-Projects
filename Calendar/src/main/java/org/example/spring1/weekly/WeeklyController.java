package org.example.spring1.weekly;

import lombok.RequiredArgsConstructor;
import org.example.spring1.weekly.model.dto.WeeklyDTO;
import org.example.spring1.weekly.model.dto.WeeklyRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(WEEKLYS)
@RequiredArgsConstructor
@CrossOrigin
public class WeeklyController {

    private final WeeklyService weeklyService;

    @GetMapping(GETWEEKLY)
    public List<WeeklyDTO> findByUserId(@RequestParam Long userId) {
        List<WeeklyDTO> weeklyData =weeklyService.findByUserId(userId);
        return weeklyData;
    }

    @PostMapping(SAVEWEEKLY)
    public void create(@RequestBody WeeklyRequestDTO dto) {
            weeklyService.createWeekly(dto);

    }

    @DeleteMapping(DELETEWEEKLY)
    public void deleteToday(@RequestParam String text) {

        weeklyService.deleteWeekly(text);

    }

}

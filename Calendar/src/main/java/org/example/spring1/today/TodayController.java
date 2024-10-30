package org.example.spring1.today;

import lombok.RequiredArgsConstructor;
import org.example.spring1.today.model.dto.TodayDTO;
import org.example.spring1.today.model.dto.TodayRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(TODAYS)
@RequiredArgsConstructor
@CrossOrigin
public class TodayController {

    private final TodayService todayService;

    @GetMapping(GETTODAY)
    public List<TodayDTO> findByUserId(@RequestParam Long userId,@RequestParam String dayOfWeek,@RequestParam String nrDay) {
        List<TodayDTO> todayData =todayService.findByUserId(userId,dayOfWeek, nrDay);
        return todayData;
    }

    @PostMapping(SAVETODAY)
    public void create(@RequestBody List<TodayRequestDTO> dto) {
        for(TodayRequestDTO today:dto) {
             todayService.createToday(today);
        }
    }

    @DeleteMapping(DELETETODAY)
    public void deleteToday(@RequestParam String text) {

            todayService.deleteToday(text);

    }

//    @PutMapping(ID_PART)
//    public TodayDTO update(@PathVariable Long id, @RequestBody ItemRequestDTO dto) {
//        return todayService.update(id, dto);
//    }


}

package org.example.spring1.monthly;

import lombok.RequiredArgsConstructor;
import org.example.spring1.monthly.model.dto.MonthlyDTO;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(MONTHLY)
@RequiredArgsConstructor
@CrossOrigin
public class MonthlyController {

    private final MonthlyService monthlyService;
 //   private String number;

//    @GetMapping(GETMONTHLY)
//    public List<MonthlyDTO> findByUserId(@RequestParam Long userId) {
//        List<MonthlyDTO> monthlyData =monthlyService.findByUserIdAndNrDay(userId,number);
//        return monthlyData;
//    }

    @PostMapping(SAVEMONTHLY)
    public void create(@RequestBody List<MonthlyRequestDTO> dto) {
        for(MonthlyRequestDTO monthly:dto) {
            monthlyService.createMonthly(monthly);
        }
    }

//    @DeleteMapping(DELETEMONTHLY)
//    public void deleteMonthly(@RequestParam String text) {
//        monthlyService.deleteMonthly(text,number);
//    }


//    @PostMapping(SENDNRMONTHLY)
//    public void squareNumber(@RequestBody int numberAsInt) {
//        number = String.valueOf(numberAsInt);
//    }

//    @GetMapping(GETNRMONTHLY)
//    public String getnumber() {
//        return number;
//    }


}

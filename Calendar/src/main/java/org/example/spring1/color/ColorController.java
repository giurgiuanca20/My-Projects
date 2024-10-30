package org.example.spring1.color;

import lombok.RequiredArgsConstructor;
import org.example.spring1.color.model.dto.ColorDTO;
import org.example.spring1.color.model.dto.ColorRequestDTO;
import org.example.spring1.monthly.model.dto.MonthlyDTO;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(COLORS)
@RequiredArgsConstructor
@CrossOrigin
public class ColorController {

    private final ColorService colorService;

    @PostMapping(UPDATECOLOR)
    public void addOrUpdateColor(@RequestParam Long userId,@RequestBody ColorRequestDTO dto) {
            colorService.addOrUpdateColor(dto,userId);
    }
    @GetMapping(GETCOLOR)
    public List<ColorDTO> findByUserId(@RequestParam Long userId) {
        List<ColorDTO> colorData =colorService.findByUserId(userId);
        return colorData;
    }

}

package org.example.spring1.color;

import lombok.RequiredArgsConstructor;
import org.example.spring1.color.model.Color;
import org.example.spring1.color.model.dto.ColorDTO;
import org.example.spring1.color.model.dto.ColorRequestDTO;
import org.example.spring1.exception.EntityNotFoundException;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorService {


    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    private final UserRepository userRepository;

    @Transactional
    public void addOrUpdateColor(ColorRequestDTO colorDto, Long userId) {
        Color existingColor = colorRepository.findByUserIdAndNrDay(userId, colorDto.getNrDay());
        if (existingColor != null) {
            existingColor.setColor(colorDto.getColor());
            colorRepository.save(existingColor);
        } else {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Cannot find user"));

            Color color=colorMapper.requestDTOToEntity(colorDto);
            color.setUser(user);
            colorRepository.save(color);

        }
    }

    public List<ColorDTO> findByUserId(Long userId) {
        List<Color> colorList = colorRepository.findByUserId(userId);
        return colorList.stream()
                .map(colorMapper::toColorDto)
                .toList();
    }




}

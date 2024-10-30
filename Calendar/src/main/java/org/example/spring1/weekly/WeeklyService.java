package org.example.spring1.weekly;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exception.EntityNotFoundException;
import org.example.spring1.today.TodayRepository;
import org.example.spring1.today.TodayTableMapper;
import org.example.spring1.today.TodayTableMapper2;
import org.example.spring1.today.model.TodayTable;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.example.spring1.weekly.model.dto.Weekly;
import org.example.spring1.weekly.model.dto.WeeklyDTO;
import org.example.spring1.weekly.model.dto.WeeklyRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyService {

  private final TodayRepository weeklyRepository;
  private final WeeklyMapper weeklyMapper;
  private final TodayTableMapper todayTableMapper;
  private final UserRepository userRepository;
  private final TodayTableMapper2 todayTableMapper2;

  public WeeklyDTO createWeekly(WeeklyRequestDTO dto) {
    User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find user"));

    Weekly weekly=weeklyMapper.requestDTOToEntity(dto);
    weekly.setUser(user);
    Weekly savedWeekly=todayTableMapper.toWeekly(weeklyRepository.save(todayTableMapper2.toTodayTableFromWeekly(weekly)));
    return weeklyMapper.toWeeklyDto(savedWeekly);
  }
  public void deleteWeekly(String text) {

    weeklyRepository.deleteByText(text,"weekly");
  }


  public List<WeeklyDTO> findByUserId(Long userId) {
    List<TodayTable> weeklyList = weeklyRepository.findByUserId(userId,"weekly");
    return weeklyList.stream()
            .map(todayTableMapper::toWeekly)
            .map(weeklyMapper::toWeeklyDto)
            .toList();
  }

}

package org.example.spring1.today;

import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.today.model.dto.Today;
import org.example.spring1.today.model.dto.TodayDTO;
import org.example.spring1.today.model.dto.TodayRequestDTO;
import org.example.spring1.weekly.model.dto.Weekly;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodayMapper {

  TodayDTO toTodayDto(Today today);
  Today toEntity(TodayDTO today);

  TodayRequestDTO entityToRequestDTO(Today today);
  Today requestDTOToEntity(TodayRequestDTO todayRequestDTO);




  Today weeklyToToday(Weekly weekly);
  Weekly todayToWeekly(Today today);

  // Metode de mapare din Monthly în Today
  Today monthlyToToday(Monthly monthly);
  Monthly todayToMonthly(Today today);

}

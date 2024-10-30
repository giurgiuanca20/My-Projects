package org.example.spring1.weekly;

import org.example.spring1.weekly.model.dto.Weekly;
import org.example.spring1.weekly.model.dto.WeeklyDTO;
import org.example.spring1.weekly.model.dto.WeeklyRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeeklyMapper {

  WeeklyDTO toWeeklyDto(Weekly weekly);

  Weekly toEntity(WeeklyDTO weekly);

  WeeklyRequestDTO entityToRequestDTO(Weekly weekly);
  Weekly requestDTOToEntity(WeeklyRequestDTO weeklyRequestDTO);
}

package org.example.spring1.monthly;

import org.example.spring1.monthly.model.dto.Monthly;
import org.example.spring1.monthly.model.dto.MonthlyDTO;
import org.example.spring1.monthly.model.dto.MonthlyRequestDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyMapper {

  MonthlyDTO toMonthlyDto(Monthly today);
  Monthly toEntity(MonthlyDTO today);

  MonthlyRequestDTO entityToRequestDTO(Monthly monthly);
  Monthly requestDTOToEntity(MonthlyRequestDTO monthlyRequestDTO);

}

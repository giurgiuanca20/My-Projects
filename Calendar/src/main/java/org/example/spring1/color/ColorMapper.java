package org.example.spring1.color;

import org.example.spring1.color.model.Color;
import org.example.spring1.color.model.dto.ColorDTO;
import org.example.spring1.color.model.dto.ColorRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {

  ColorDTO toColorDto(Color color);
  Color toEntity(ColorDTO color);

  ColorRequestDTO entityToRequestDTO(Color color);
  Color requestDTOToEntity(ColorRequestDTO colorRequestDTO);

}

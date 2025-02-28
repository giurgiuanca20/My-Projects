package org.example.spring1.color.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorRequestDTO {
  private String color;
  private String nrDay;
  private Long userId;
}

package org.example.spring1.monthly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyDTO {
  private Long id;
  private String image;
  private String text;
  private String hour;
  private String nrDay;
}

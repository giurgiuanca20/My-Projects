package org.example.spring1.weekly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyDTO {
  private Long id;
  private String day;
  private String text;
  private String image;
  private String hour;

}

package org.example.spring1.today.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodayDTO {
  private Long id;
  private String image;
  private String text;
  private String hour;
}

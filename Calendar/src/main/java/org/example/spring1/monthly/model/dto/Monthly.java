package org.example.spring1.monthly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring1.user.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Monthly {
    private Long id;
    private String nrDay;
    private String text;
    private String image;
    private String hour;
    private User user;

}
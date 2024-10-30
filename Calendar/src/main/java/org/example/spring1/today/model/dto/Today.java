package org.example.spring1.today.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring1.user.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Today {
    private Long id;
    private String image;
    private String text;
    private String hour;
    private User user;

}

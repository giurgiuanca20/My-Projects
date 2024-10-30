package org.example.spring1.color.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring1.user.model.User;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Color {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String color;
  @Column(length = 100, nullable = false)
  private String nrDay;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}

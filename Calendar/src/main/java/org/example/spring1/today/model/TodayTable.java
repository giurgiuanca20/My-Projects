package org.example.spring1.today.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring1.user.model.User;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TodayTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String image;
    @Column(length = 100, nullable = false)
    private String text;
    @Column(length = 100, nullable = false)
    private String hour;
    @Column(length = 100, nullable = false)
    private String type;
    @Column(length = 100)
    private String day;
    @Column(length = 100)
    private String nrDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

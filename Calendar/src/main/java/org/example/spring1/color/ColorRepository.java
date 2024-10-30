package org.example.spring1.color;


import org.example.spring1.color.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {


    List<Color> findByUserId(Long userId);

    Color findByUserIdAndNrDay(Long userId, String nrDay);


}

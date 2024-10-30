package org.example.spring1.today;


import org.example.spring1.today.model.TodayTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodayRepository extends JpaRepository<TodayTable, Long> {

    @Query("SELECT t FROM TodayTable t WHERE t.user.id = :userId AND t.type = :type AND t.nrDay = :nrDay")
    List<TodayTable> findByUserIdAndNrDay(@Param("userId") Long userId, @Param("nrDay") String nrDay, @Param("type") String type);

    @Query("SELECT t FROM TodayTable t WHERE t.user.id = :userId AND t.type = :type")
    List<TodayTable> findByUserId(Long userId,String type);
    @Transactional
    @Modifying
    @Query("DELETE FROM TodayTable t WHERE t.text = :text AND t.type = :type")
    void deleteByText(@Param("text") String text, @Param("type") String type);

    @Transactional
    @Modifying
    @Query("DELETE FROM TodayTable t WHERE t.text = :text AND t.nrDay = :nrDay AND t.type = :type")
    void deleteByTextAndNrDay(@Param("text") String text, @Param("nrDay") String nrDay, @Param("type") String type);
}

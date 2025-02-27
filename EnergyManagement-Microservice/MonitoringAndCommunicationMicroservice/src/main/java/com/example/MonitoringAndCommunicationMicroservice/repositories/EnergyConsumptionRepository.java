package com.example.MonitoringAndCommunicationMicroservice.repositories;

import com.example.MonitoringAndCommunicationMicroservice.entities.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption,Long> {

    @Query("SELECT e FROM EnergyConsumption e WHERE e.deviceId = :deviceId AND e.timestamp BETWEEN :startOfDay AND :endOfDay")
    List<EnergyConsumption> getEnergyConsumptionForDay(@Param("deviceId") String deviceId, @Param("startOfDay") Long startOfDay, @Param("endOfDay") Long endOfDay);

}

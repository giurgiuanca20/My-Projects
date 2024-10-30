package com.example.DeviceManagement.repositories;

import com.example.DeviceManagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    Boolean existsByDescription(String description);

    Optional<Device> findByDescriptionAndAddressAndMaxHourlyConsumption(String description,String address, int maxHourlyConsumption);
    Optional<Device> findByDescription(String description);
    void deleteByDescriptionAndAddressAndMaxHourlyConsumption(String description,String address, int maxHourlyConsumption);
    @Modifying
    @Transactional
    @Query("UPDATE Device d SET d.userId = :userId WHERE d.description = :description")
    void updateUserIdByDescription(String description,Long userId);

    @Modifying
    @Query("UPDATE Device d SET d.userId = null WHERE d.userId = :userId AND d.description = :description")
    void deleteUserIdByUserIdAndDescription(Long userId, String description);

    @Modifying
    @Query("UPDATE Device d SET d.userId = null WHERE d.userId = :userId")
    void deleteUserIdByUserId(Long userId);

    List<Device> findByUserId(Long userId);
}

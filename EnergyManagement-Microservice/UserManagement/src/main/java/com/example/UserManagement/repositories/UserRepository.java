package com.example.UserManagement.repositories;

import com.example.UserManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findIdByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void deleteByUsername(String username);

}

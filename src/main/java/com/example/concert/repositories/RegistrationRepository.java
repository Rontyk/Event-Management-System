package com.example.concert.repositories;

import com.example.concert.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository  extends JpaRepository<Registration, Long> {
    Registration getRegistrationByRegistrationId(Long id);

    List<Registration> findRegistrationsByUserUserId(Long id);
    Registration findRegistrationByUserUserIdAndEventEventId(Long userId, Long eventId);
}

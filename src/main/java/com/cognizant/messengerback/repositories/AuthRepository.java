package com.cognizant.messengerback.repositories;

import com.cognizant.messengerback.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AuthRepository extends JpaRepository<AuthUser,Integer> {
    AuthUser findByUsername(String username);
}

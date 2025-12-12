package com.hostpalace.infrasight.modules.auth.repository;

import com.hostpalace.infrasight.modules.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    // FIXED TYPO: exiexists -> exists
    boolean existsByEmail(String email);
}
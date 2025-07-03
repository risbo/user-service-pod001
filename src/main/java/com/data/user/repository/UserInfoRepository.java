package com.data.user.repository;

import com.data.user.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, UUID> {

    @EntityGraph(attributePaths = "phones")
    Optional<UserInfoEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}

package com.data.user.service;

import jakarta.validation.Payload;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserInfoFetchingService {

    boolean existsByEmail(final String email);

    ResponseEntity<Payload> buildUserDetailsById(final UUID id);
}

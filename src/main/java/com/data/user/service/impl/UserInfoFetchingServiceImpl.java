package com.data.user.service.impl;


import com.data.user.domain.UserInfoEntity;
import com.data.user.api.output.UserInfoDetails;
import com.data.user.repository.UserInfoRepository;
import com.data.user.security.JwtUtil;
import com.data.user.service.UserInfoFetchingService;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInfoFetchingServiceImpl implements UserInfoFetchingService {

    private final UserInfoRepository userInfoRepository;


    public UserInfoFetchingServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;

    }


    @Override
    public boolean existsByEmail(String email) {
        return userInfoRepository.existsByEmail(email);
    }

    public ResponseEntity<Payload> buildUserDetailsById(final UUID id) {
        UserInfoEntity userInfo = userInfoRepository.findById(id).get();

        UserInfoDetails details = new UserInfoDetails();
        details.setId(userInfo.getId().toString());
        details.setCreated(userInfo.getCreated().toEpochSecond());
        details.setModified(userInfo.getModified().toEpochSecond());
        details.setLast_login(userInfo.getLastLogin().toEpochSecond());
        details.setToken(userInfo.getToken());
        details.setIsactice(userInfo.isActive());

        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

}


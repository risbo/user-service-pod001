package com.data.user.outbound.impl;

import com.data.user.api.input.UserInfo;
import com.data.user.api.output.Message;
import com.data.user.domain.UserInfoEntity;
import com.data.user.outbound.UserInfoOutbound;
import com.data.user.service.UserInfoCreationService;
import com.data.user.service.UserInfoFetchingService;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.data.user.messages.UserInfoMessages.*;

@Service
public class UserInfoOutboundImpl implements UserInfoOutbound {

    @Value("${email-standard-regex}")
    private String emailPattern;

    @Value("${security.password.pattern}")
    private String passwordPattern;


    private final UserInfoCreationService userInfoCreation;
    private final UserInfoFetchingService userInfoFetchingService;

    public UserInfoOutboundImpl(UserInfoCreationService userInfoCreation, UserInfoFetchingService userInfoFetchingService) {
        this.userInfoCreation = userInfoCreation;
        this.userInfoFetchingService = userInfoFetchingService;
    }

    @Override
    public Mono<ResponseEntity<Payload>> registerNewUserApp(final UserInfo userInfo) {

        if (!userInfo.getEmail().matches(emailPattern)) {
            return Mono.just(ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Message(EMAIL_INVALID)));
        }

        if (!userInfo.getPassword().matches(passwordPattern)) {
            return Mono.just(ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Message(PASSWORD_WITHOUT_FORMAT)));
        }



        if (userInfoFetchingService.existsByEmail(userInfo.getEmail())) {
            return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new Message(EXISING_EMAIL)));
        }
        try {
            final UserInfoEntity userInfoEntityCreated = userInfoCreation.createUserInfo(userInfo);
            if (userInfoEntityCreated.getId() == null) {
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new Message(ERROR_CREATING_USER)));
            }
            return Mono.just(userInfoFetchingService.buildUserDetailsById(userInfoEntityCreated.getId()));
        } catch (Exception ex) {
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message(ERROR_SERVER)));
        }

    }

}

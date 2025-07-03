package com.data.user.outbound;

import com.data.user.api.input.UserInfo;
import jakarta.validation.Payload;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface UserInfoOutbound {

    Mono<ResponseEntity<Payload>> registerNewUserApp(final UserInfo userInfo);
}

package com.data.user.api;

import com.data.user.api.input.PhoneInfo;
import com.data.user.api.input.UserInfo;
import com.data.user.api.output.Message;
import com.data.user.outbound.UserInfoOutbound;
 import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@SpringBootTest
class UserInfoControllerTest {

    private UserInfoController userInfoController;
    private UserInfoOutbound userInfoOutbound;
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        userInfoOutbound = Mockito.mock(UserInfoOutbound.class);
        userInfoController = new UserInfoController(userInfoOutbound);
        webTestClient = WebTestClient.bindToController(userInfoController).build();
    }

    @Test
    void register_validUser_shouldReturnHttp200() {
        UserInfo userInfo = buildUserInfo("test@bci.com", "Boris Palacios", "abc123!");

        Mockito.when(userInfoOutbound.registerNewUserApp(any(UserInfo.class)))
                .thenReturn(Mono.just(ResponseEntity.ok(new Message("OK"))));

        webTestClient.post()
                .uri("/api/user/register")
                .bodyValue(userInfo)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Disabled("Este test está deshabilitado temporalmente")
    void register_invalidEmail_shouldReturnHttp422_andMensaje() {
        UserInfo userInfo = buildUserInfo("invalid-email", "Boris Palacios", "abc123!");

        webTestClient.post()
                .uri("/api/user/register")
                .bodyValue(userInfo)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody()
                .jsonPath("$.mensaje").isEqualTo("Correo electrónico inválido");
    }


    @Test
    void register_emailAlreadyExists_shouldReturnHttp302() {
        UserInfo userInfo = buildUserInfo("test@bci.com", "Boris Palacios", "abc123!");

         Mockito.when(userInfoOutbound.registerNewUserApp(any(UserInfo.class)))
                .thenReturn(Mono.just(ResponseEntity.status(302).body(new Message("El correo ya está registrado."))));

        webTestClient.post()
                .uri("/api/user/register")
                .bodyValue(userInfo)
                .exchange()
                .expectStatus().isEqualTo(302);
    }


    private UserInfo buildUserInfo(String email, String name, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName(name);
        userInfo.setPassword(password);

        PhoneInfo phone = new PhoneInfo();
        phone.setNumber("5730077733");
        phone.setCitycode("57");
        phone.setCountrycode("1");

        userInfo.setPhones(List.of(phone));
        return userInfo;
    }
}


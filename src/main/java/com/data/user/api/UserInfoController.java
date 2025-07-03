package com.data.user.api;

import com.data.user.api.input.UserInfo;
import com.data.user.api.output.Message;
import com.data.user.outbound.UserInfoOutbound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Payload;
 import jakarta.validation.Validation;
 import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

 import java.util.Set;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Operaciones relacionadas con usuarios")
@CrossOrigin("*")
public class UserInfoController {


    private final UserInfoOutbound userInfoOutbound;

    public UserInfoController(UserInfoOutbound userInfoOutbound) {
        this.userInfoOutbound = userInfoOutbound;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Registrar nuevo usuario",
            description = "Recibe los datos de un usuario y lo registra en el sistema"
    )
    public Mono<ResponseEntity<Payload>> register(@RequestBody Mono<UserInfo> userInfoMono) {
        return userInfoMono.flatMap(userInfo -> {
            Set<ConstraintViolation<UserInfo>> violations = Validation.buildDefaultValidatorFactory()
                    .getValidator()
                    .validate(userInfo);

            if (!violations.isEmpty()) {
                String mensajeTexto = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));

                Message mensaje = new Message(mensajeTexto);
                return Mono.just(ResponseEntity.badRequest().body(mensaje));
            }

            return userInfoOutbound.registerNewUserApp(userInfo);
        });
    }
}


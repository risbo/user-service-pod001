package com.data.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Test Controller")
@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController {

    @Operation(summary = "Ping de prueba")
    @GetMapping
    public Mono<String> test() {
        return Mono.just("pong");
    }
}


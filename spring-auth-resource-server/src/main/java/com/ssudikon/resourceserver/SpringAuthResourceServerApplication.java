package com.ssudikon.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@EnableMethodSecurity
public class SpringAuthResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthResourceServerApplication.class, args);
    }

}

@Service
class HelloService {

    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public Map<String, String> hello() {

        var jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Map.of("message", "Hello " + jwt.getSubject());
    }
}

@RestController
class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    @GetMapping("/")
    public Map<String, String> hello() {
        return helloService.hello();
    }
}

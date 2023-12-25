package com.ssudikon.springauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class SpringAuthJoshLongApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthJoshLongApplication.class, args);
    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        var saUser = User.withDefaultPasswordEncoder()
                .roles("user")
                .username("sa")
                .password("sa")
                .build();
        var adminUser = User.withDefaultPasswordEncoder()
                .roles("admin")
                .username("admin")
                .password("admin")
                .build();

        return new InMemoryUserDetailsManager(saUser, adminUser);
    }

}

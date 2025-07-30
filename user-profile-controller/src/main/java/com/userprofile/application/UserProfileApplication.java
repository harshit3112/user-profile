package com.userprofile.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.userprofile.controller",
    "com.userprofile.service",
    "com.userprofile.repository",
    "com.userprofile.config"
})
@EntityScan(basePackages = "com.userprofile.repository.entity")
@EnableJpaRepositories(basePackages = "com.userprofile.repository")
public class UserProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }
}

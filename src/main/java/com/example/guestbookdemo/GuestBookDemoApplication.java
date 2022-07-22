package com.example.guestbookdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuestBookDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookDemoApplication.class, args);
    }

}

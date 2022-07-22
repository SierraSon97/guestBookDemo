package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookServiceTest {
    @Autowired
    private GuestBookService guestBookService;

    @Test
    public void testRegister(){
        GuestBookDto guestBookDto = GuestBookDto.builder()
                .title("Sample title...")
                .content("Sample content...")
                .writer("User")
                .build();
        System.out.println(guestBookService.register(guestBookDto));
    }
}
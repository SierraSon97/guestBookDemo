package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.dto.PageResultDto;
import com.example.guestbookdemo.entity.GuestBook;
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

    @Test
    public void testList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .build();
        PageResultDto<GuestBookDto, GuestBook> resultDto = guestBookService.getList(pageRequestDto);

        System.out.println("PREV: " + resultDto.isPrev());
        System.out.println("NEXT: " + resultDto.isNext());
        System.out.println("TOTAL: " + resultDto.getTotalPage());
        System. out. println("------------------------------------------------------------");
        for(GuestBookDto guestBookDto : resultDto.getDtoList()){
            System.out.println(guestBookDto);
        }

        System.out.println("========================================");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }


}
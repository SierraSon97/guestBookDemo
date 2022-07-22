package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.dto.PageResultDto;
import com.example.guestbookdemo.entity.GuestBook;
import com.example.guestbookdemo.entity.QGuestBook;
import com.example.guestbookdemo.service.GuestBookService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookRepositoryTest {
    @Autowired
    private GuestBookRepository guestBookRepository;

    @Autowired
    private GuestBookService guestBookService;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i ->{
            GuestBook guestBook = GuestBook.builder()
                    .title("title..." + i)
                    .content("Content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestBookRepository.save(guestBook));
        });
    }

    @Test
    public void updateTest(){
        Optional<GuestBook> result = guestBookRepository.findById(300L);

        if(result.isPresent()){
            GuestBook guestBook = result.get();
            guestBook.setTitle("Changed Title...");
            guestBook.setContent("Changed Content...");

            guestBookRepository.save(guestBook);
        }
    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestBook.title.contains(keyword);

        BooleanExpression exContent = qGuestBook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestBook.gno.gt(0L));

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });

    }

    @Test
    public void testSearch(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();
        PageResultDto<GuestBookDto, GuestBook> resultDto = guestBookService.getList(pageRequestDto);
        System.out.println("PREV: "+resultDto.isPrev());
        System.out.println("NEXT: "+resultDto.isNext());
        System.out.println("TOTAL: " + resultDto.getTotalPage());
        System. out. println("------------------------------------------------------------");
        for (GuestBookDto guestbookDTO : resultDto.getDtoList()) { System.out.println(guestbookDTO);
        }
        System.out.println("========================================");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }
}
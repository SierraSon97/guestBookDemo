package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.entity.GuestBook;
import com.example.guestbookdemo.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GusetBookServiceImpl implements GuestBookService{

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long register(GuestBookDto guestBookDto){

        log.info("DTO---------------------");
        log.info(guestBookDto);

        GuestBook entity = dtoToEntity(guestBookDto);
        log.info(entity);

        guestBookRepository.save(entity);
        return entity.getGno();
    }
}

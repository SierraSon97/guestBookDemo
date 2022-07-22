package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.dto.PageResultDto;
import com.example.guestbookdemo.entity.GuestBook;
import com.example.guestbookdemo.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

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

    @Override
    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto){
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        Page<GuestBook> result = guestBookRepository.findAll(pageable);
        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}

package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.dto.PageResultDto;
import com.example.guestbookdemo.entity.GuestBook;

public interface GuestBookService {
    Long register(GuestBookDto dto);

    PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto);

    default GuestBook dtoToEntity(GuestBookDto dto){
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestBookDto entityToDto(GuestBook entity){
        GuestBookDto dto = GuestBookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}

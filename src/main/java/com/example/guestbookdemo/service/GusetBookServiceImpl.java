package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.dto.PageResultDto;
import com.example.guestbookdemo.entity.GuestBook;
import com.example.guestbookdemo.entity.QGuestBook;
import com.example.guestbookdemo.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        BooleanBuilder booleanBuilder = getSearch(requestDto);
        Page<GuestBook> result = guestBookRepository.findAll(booleanBuilder,pageable);
        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public GuestBookDto read(Long gno){
        Optional<GuestBook> result = guestBookRepository.findById(gno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void remove(Long gno){
        guestBookRepository.deleteById(gno);
    }

    @Override
    public void modify(GuestBookDto dto){
        Optional<GuestBook> result = guestBookRepository.findById(dto.getGno());
        if(result.isPresent()){
            GuestBook entity = result.get();
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            guestBookRepository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto){
        String type = requestDto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestBook qGuestBook = QGuestBook.guestBook;
        String keyword = requestDto.getKeyword();

        BooleanExpression expression = qGuestBook.gno.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }
}

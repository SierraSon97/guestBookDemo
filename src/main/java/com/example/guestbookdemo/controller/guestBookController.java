package com.example.guestbookdemo.controller;

import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestBook")
@Log4j2
@RequiredArgsConstructor
public class guestBookController {

    private final GuestBookService guestBookService;

    @GetMapping("/main")
    public String gusetBookMain(PageRequestDto pageRequestDto, Model model){
        log.info("mainMenu-----------");
        model.addAttribute("result", guestBookService.getList(pageRequestDto));
        return "guestBook/main";
    }
}

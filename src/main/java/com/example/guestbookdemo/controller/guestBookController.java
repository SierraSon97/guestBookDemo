package com.example.guestbookdemo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestBook")
@Log4j2
public class guestBookController {
    @GetMapping("/main")
    public String gusetBookMain(){
        log.info("mainMenu-----------");
        return "guestBook/main";
    }
}

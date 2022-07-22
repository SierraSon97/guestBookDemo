package com.example.guestbookdemo.controller;

import com.example.guestbookdemo.dto.GuestBookDto;
import com.example.guestbookdemo.dto.PageRequestDto;
import com.example.guestbookdemo.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestBookDto guestBookDto, RedirectAttributes redirectAttributes){
        log.info("dto..."+guestBookDto);

        Long gno = guestBookService.register(guestBookDto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestBook/main";
    }
}

package org.iclass.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.BookUser;
import org.iclass.mvc.service.BookUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@SessionAttributes(names = {"user"})
public class MemberController {

    private final BookUserService service;

    @GetMapping("/welcome")
    public void welcome(){

    }

    @GetMapping("/join")
    public void joinView(){

    }

    @PostMapping("/join")
    public String joinAction(BookUser dto , RedirectAttributes redirectAttributes){
        int result = service.join(dto);
        String message = "";

        if(result == 1)
            message = "회원가입이 완료되었습니다.";
        else
            message = "회원 가입 실패";

        redirectAttributes.addFlashAttribute("message",message);

        return "redirect:/member/welcome";
    }

    @GetMapping("/login")
    public void login(){

    }

    @PostMapping("/login")
    public String loginProc(@RequestParam Map<String,String> param , RedirectAttributes redirectAttributes , Model model){
        String url = "/";

        BookUser member = service.login(param);

        if(member == null){
            redirectAttributes.addFlashAttribute("incorrect","y");
            url = "login";
        } else {
            model.addAttribute("user",member);
        }

        return "redirect:"+url;
    }

    @GetMapping("logout")
    public String logout(SessionStatus session){
        session.setComplete();

        return "redirect:/";
    }
}

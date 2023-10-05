package org.iclass.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService service;

//    @GetMapping("/list")
//    public void pagelist(@RequestParam(defaultValue = "1") int page , Model model){
//        model.addAttribute("list",service.pagelist(page).get("list"));
//        model.addAttribute("paging",service.pagelist(page).get("paging"));
//        model.addAttribute("today", LocalDate.now());
//    }

    @GetMapping("/list")
    public void pagelist(PageRequestDTO pageRequestDTO , Model model){
        PageResponseDTO pageResponseDTO = service.listWithSearch(pageRequestDTO);
        //list.html 에 전달할 model 관련 코드 작성하기 + list.html 도 완성하기 + 레이아웃도 적용하기 (10/04)
        model.addAttribute("list",service.pagelist(pageRequestDTO));
        model.addAttribute("paging",pageResponseDTO);
        model.addAttribute("page",pageRequestDTO.getPage());
        model.addAttribute("today",LocalDate.now());
    }

//    @GetMapping("/read")
//    public void read(long idx , @ModelAttribute("page") int page , Model model){
//        model.addAttribute("vo",service.read(idx));
//        model.addAttribute("cmtlist",service.commentsList(idx));
//    }

    @GetMapping("/read")
    public void read(PageRequestDTO pageRequestDTO , long idx , Model model){
        Community community = service.read(idx);
        model.addAttribute("dto",community);
    }

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String save(Community dto , RedirectAttributes redirectAttributes){
        service.insert(dto);

        redirectAttributes.addFlashAttribute("message","글 등록이 완료되었습니다.");

        return "redirect:/community/list";
    }

    @GetMapping("/update")
    public void updateView(long idx , @ModelAttribute("page") int page , Model model){
        model.addAttribute("vo",service.selectByIdx(idx));
    }

    @PostMapping("/update")
    public String updateAction(Community vo , int page , RedirectAttributes redirectAttributes){
        service.update(vo);

        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addAttribute("idx",vo.getIdx());
        redirectAttributes.addFlashAttribute("message","글 수정이 완료되었습니다.");

        return "redirect:/community/read";
    }

    @PostMapping("delete")
    public String delete(int page , long idx , RedirectAttributes redirectAttributes){
        service.delete(idx);

        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addFlashAttribute("message","글 삭제가 완료되었습니다.");

        return "redirect:/community/list";
    }

    @PostMapping("/comments")
    public String comments(int page , int f , CommunityComments dto , RedirectAttributes redirectAttributes){
        String message = "";

        service.comments(dto,f);
        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addAttribute("idx",dto.getMref());

        if(f==1)
            message = "댓글이 등록되었습니다.";

        redirectAttributes.addFlashAttribute("message",message);

        return "redirect:/community/read";
    }

}

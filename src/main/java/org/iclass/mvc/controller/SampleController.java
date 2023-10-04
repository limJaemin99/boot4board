package org.iclass.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/sample")	//url 이 community 로 시작하면 DicpatcherServlet 으로부터 
								//	CommunityController 가 위임 받아 처리한다.
public class SampleController {

	private final CommunityService service;

	private SampleController(CommunityService service) {
		this.service = service;
	}


	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("message","안녕하세요");
		model.addAttribute("list", List.of("모모","나연","nana","쯔위"));
	}


	@GetMapping("/spring")
	public void spring(Community community,				//▼ 같이 사용할 수 없음
					   @RequestParam(required = false) /*@ModelAttribute("name")*/ String name,
					   @RequestParam(required = false) Integer age,
					   Model model) {
		log.info("파라미터 name : {}",name);
		log.info("파라미터 age : {}",age);
		log.info("Community dto : {}",community);	//Community 클래스의 모든 필드들이 파라미터로 전달될 수 있다.
		//@RequestParam(required = false) 로 하면 파라미터 값이 null 이 되어야 한다.
		//따라서 int , long 들은 Integer , Long 과 같이 래퍼 타입으로 선언한다.
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		//★★★중요★★★ - 복습 : 10/04
		//DTO 클래스는 파라미터를 받고, 다시 View 의 Model(View 로 전달하는 데이터 저장 객체)로 전달도 할 수 있다.
			//▶ 이때 model 에 저장된 데이터의 이름은 DTO 변수명과 같다.
		//int , long 등 기본형과 String 은 파라미터 받은것을 Model 에 직접 저장해야 View 에 전달된다.
	}

}

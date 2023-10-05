package org.iclass.mvc.service;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dao.CommunityMapper;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
class CommunityServiceTest {

    @Autowired
    CommunityMapper dao;

    @Autowired
    CommunityService service;

    @Test
    void pagelist() {
        //DTO 는 Controller 를 사용하면 직접 받을 수 있으므로, 아래 DTO 는 생성자로 값 설정은 테스트용이다.
//        PageRequestDTO dto = new PageRequestDTO(1,0,0,0,new String[]{"t","c"},null,"회원",null,null);
        //▼ View 에서 받는 type
        PageRequestDTO dto = new PageRequestDTO(1,0,0,0,null,"tc","공지",null,null,null);
        List<Community> list = service.pagelist(dto);
        list.forEach(i -> {
            log.info("★★★★★ 글 : {}",i);
        });
    }

}
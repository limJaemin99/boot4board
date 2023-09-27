package org.iclass.mvc.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.mvc.dto.Community;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CommunityMapperTest {

    @Autowired
    CommunityMapper dao;

    @Test
    void pagelist() {
    }

    @DisplayName("저장된 글의 개수는 0이 아닙니다.")
    @Test
    void count() {
        int count = dao.count();
        log.info("커뮤니티 count : {}",count);
        Assertions.assertNotEquals(0,count);
    }

    @DisplayName("지정된 글 번호로 조회된 글이 있어야 합니다.")
    @Test
    void selectByIdx() {
        Community dto = dao.selectByIdx(500);
        Assertions.assertNotNull(dto);
    }

    @Test
    void setReadCount() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
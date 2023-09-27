package org.iclass.mvc.service;

import java.util.Map;

import org.iclass.mvc.dao.BookUserMapper;
import org.iclass.mvc.dto.BookUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookUserService {
	
	private final BookUserMapper dao;
	
	
	//로그인
	public BookUser login(Map<String, String> map) {
		return dao.login(map);
	}
	

	//회원가입
	@Transactional
	public int join(BookUser dto) {
		return dao.join(dto);
	}
}

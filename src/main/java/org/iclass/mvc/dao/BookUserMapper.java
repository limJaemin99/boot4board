package org.iclass.mvc.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.BookUser;
@Mapper
public interface BookUserMapper {
	
	BookUser login(Map<String, String> map);
	int join(BookUser dto);
}

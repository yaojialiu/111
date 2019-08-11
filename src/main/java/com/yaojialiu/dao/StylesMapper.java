package com.yaojialiu.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StylesMapper {
	@Insert("insert into cms_styles(styles) values(#{value})")
	int insert(String value);
	
	@Select("select * from cms_styles")
	Map selects();
	
	@Delete("delete from cms_styles")
	int deletes();
}

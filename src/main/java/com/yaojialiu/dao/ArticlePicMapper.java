package com.yaojialiu.dao;

import java.util.List;
import java.util.Map;

import com.yaojialiu.domain.ArticlePic;

public interface ArticlePicMapper {
	List<Map> selects();
	Map select(Integer id);
	int insert(ArticlePic articlePic);
}

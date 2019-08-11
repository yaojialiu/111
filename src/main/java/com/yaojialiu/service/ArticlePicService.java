package com.yaojialiu.service;

import java.util.List;
import java.util.Map;

import com.yaojialiu.domain.ArticlePic;

public interface ArticlePicService {
	List<Map> selects();
	Map select(Integer id);
	int insert(ArticlePic articlePic);
}

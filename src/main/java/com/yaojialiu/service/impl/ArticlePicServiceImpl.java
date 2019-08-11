package com.yaojialiu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaojialiu.dao.ArticleMapper;
import com.yaojialiu.dao.ArticlePicMapper;
import com.yaojialiu.domain.Article;
import com.yaojialiu.domain.ArticlePic;
import com.yaojialiu.service.ArticlePicService;
import com.yaojialiu.service.ArticleService;

@Service
public class ArticlePicServiceImpl implements ArticlePicService {
	@Resource
	private ArticlePicMapper mapper;

	@Override
	public List<Map> selects() {
		return mapper.selects();
	}

	@Override
	public Map select(Integer id) {
		return mapper.select(id);
	}

	@Override
	public int insert(ArticlePic articlePic) {
		return mapper.insert(articlePic);
	}

	
}

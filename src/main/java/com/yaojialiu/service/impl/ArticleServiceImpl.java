package com.yaojialiu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaojialiu.dao.ArticleMapper;
import com.yaojialiu.domain.Article;
import com.yaojialiu.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	private ArticleMapper articleMapper;
	

	@Override
	public int insert(Article article) {
		return articleMapper.insert(article);
	}


	@Override
	public Map get(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.get(id);
	}


	@Override
	public List<Map> getTitles(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.getTitles(article);
	}


	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}

}

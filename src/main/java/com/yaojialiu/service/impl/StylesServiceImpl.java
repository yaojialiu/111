package com.yaojialiu.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaojialiu.dao.StylesMapper;
import com.yaojialiu.service.StylesService;
import com.yaojialiu.utils.CMSRuntimeException;
@Service
public class StylesServiceImpl implements StylesService {
	@Resource
	private StylesMapper mapper;

	@Override
	public boolean setStyles(String value) {
		
		try {
			mapper.deletes();
			mapper.insert(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSRuntimeException("设置失败");
		}
		
	}

	@Override
	public Map selects() {
		return mapper.selects();
	}
	
}

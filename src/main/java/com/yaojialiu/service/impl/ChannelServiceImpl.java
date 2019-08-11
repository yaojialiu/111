package com.yaojialiu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaojialiu.dao.ChannelMapper;
import com.yaojialiu.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Resource
	private ChannelMapper channelMapper;

	@Override
	public List<Map> selectChannels() {
		return channelMapper.selectChannels();
	}

	@Override
	public List<Map> selectCategorys(Integer channelId) {
		return channelMapper.selectCategorys(channelId);
	}

}

package com.yaojialiu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yaojialiu.domain.Article;
import com.yaojialiu.service.ArticlePicService;
import com.yaojialiu.service.ArticleService;
import com.yaojialiu.service.ChannelService;
import com.yaojialiu.service.StylesService;
import com.yaojialiu.utils.CMSConstant;
import com.yaojialiu.vo.ArticlePicVO;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class IndexController {
	
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private  ArticleService articleService;
	
	@Resource
	private ArticlePicService articlePicService;
	
	@Resource 
	private StylesService stylesService;
	/**
	 * 
	 * @Title: toIndex
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 * @throws InterruptedException
	 */
	@GetMapping({ "/", "index", "toIndex" })
	public String toIndex(Article article, Model model) throws InterruptedException {
		//查询条件 所有显示的文章都是审核过的
		article.setStatus(CMSConstant.ARTICLE_STATUS_CHECKED);
		 //封装到model
		model.addAttribute("article", article);
		
		Map map = stylesService.selects();
		model.addAttribute("styles",map);
		
		//定义6个线程
		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		Thread t6 = null;
		//线程1	 返回所有栏目
		 t1 = new Thread(new Runnable() {

			@Override
			public void run() {
			
				List<Map> channels = channelService.selectChannels();
			
					model.addAttribute("channels", channels);
				
			}
		});
		
     //线程2  	// 如果用户没有点击栏目,就默认显示热点文章
		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
			
				if (null == article.getChannelId()) {
					article.setHot(1);// 1 热点文章
					List<Map> hotArticles = articleService.getTitles(article);
					model.addAttribute("hotArticles", hotArticles);
				}
			}
		});
		
		
		//线程3 // 查询栏目下的所有分类
		t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				if (null != article.getChannelId()) {
					
					List<Map> categorys = channelService.selectCategorys(article.getChannelId());
					model.addAttribute("categorys", categorys);
				}
			}
		});

		// 线程4  具体栏目或分类下的文章
		 t4 = new Thread(new Runnable() {

			@Override
			public void run() {

				List<Map> articles = articleService.getTitles(article);
				model.addAttribute("articles", articles);
			}
		});

		// 线程5 获取最新文章
		 t5 = new Thread(new Runnable() {

			@Override
			public void run() {

				int pageSize = 10;
				PageHelper.startPage(0, pageSize);
				List<Map> lasts = articleService.getTitles(null);
				model.addAttribute("lasts", lasts);
			}
		});
		 
		 
			// 线程6 获取最新文章
		 t6 = new Thread(new Runnable() {

			@Override
			public void run() {
				Gson gson = new Gson();
				List<ArticlePicVO> pics = new ArrayList<>();
				List<Map> maps = articlePicService.selects();
				
				for (Map map : maps) {
					String str = (String) map.get("content");
					JsonElement jsonElement = new JsonParser().parse(str);
					JsonArray jsonArray = jsonElement.getAsJsonArray();
					for(JsonElement element : jsonArray) {
						ArticlePicVO vo = gson.fromJson(element, ArticlePicVO.class);
						vo.setId((Integer)map.get("id"));
						pics.add(vo);
						break;
					}
					
				}
				model.addAttribute("pics",pics);
			}
		});
		//运行线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		//让线程都执行完,主线程最后执行
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		return "index/index";
	}

}

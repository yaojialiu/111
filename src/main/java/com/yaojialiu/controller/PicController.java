package com.yaojialiu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yaojialiu.domain.ArticlePic;
import com.yaojialiu.service.ArticlePicService;
import com.yaojialiu.vo.ArticlePicVO;

@RequestMapping("pics")
@Controller
public class PicController {
	@Resource
	private ArticlePicService service;
	Gson gson = new Gson();
	
	@GetMapping("publishPic")
	public String publishPic() {
		return "my/article/publishpic";
	}
	
	@GetMapping("select")
	public String select(Integer id,Model model) {
		Map map = service.select(id);
		
		String str = (String) map.get("content");
		JsonElement jsonElement = new JsonParser().parse(str);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		List<ArticlePicVO> list = new ArrayList<>();
		for(JsonElement element : jsonArray) {
			ArticlePicVO vo = gson.fromJson(element, ArticlePicVO.class);
			list.add(vo);
		}
		model.addAttribute("pics",list);
		model.addAttribute("title",map.get("title"));
		return "my/article/pics";
	}
	
	
	@PostMapping("publishpic")
	public String publishpic(MultipartFile[] files,String[] picContents,ArticlePic articlePic) {
		String path = "e:/pic/";
		
		List<ArticlePicVO> list = new ArrayList<>();
		
		if(files!=null && files.length>0) {
			for (int i = 0; i < files.length; i++) {
				// 上传的文件原始名称
				String originalFilename = files[i].getOriginalFilename();
				// 为防止名称重复.文件改名为uuid
				String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

				File f = new File(path + filename);
				
				ArticlePicVO vo = new ArticlePicVO();
				vo.setId(i);
				vo.setContent(picContents[i]);
				vo.setUrl(filename);
				list.add(vo);
				
				
				try {
					files[i].transferTo(f);// 把文件写入硬盘
					// 封装图片
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		String json = gson.toJson(list);
		articlePic.setContent(json);
		service.insert(articlePic);
		return "redirect:/my/";
	}
}

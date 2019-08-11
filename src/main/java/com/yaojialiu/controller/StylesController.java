package com.yaojialiu.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaojialiu.service.StylesService;
import com.yaojialiu.utils.TileEnum;

@RequestMapping("styles")
@Controller
public class StylesController {
	@Resource
	private StylesService service;
	
	@GetMapping("settings")
	public String setting(Model model) {
		TileEnum[] styles = TileEnum.values();
		model.addAttribute("styles",styles);
		
		Map map = service.selects();
		model.addAttribute("map",map);
		return "admin/settings";
	}
	
	@ResponseBody
	@RequestMapping("settings")
	public boolean save(String styles) {
		return service.setStyles(styles);
	}
	
}

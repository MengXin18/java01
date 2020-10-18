package com.mx.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mx")
public class HelloSpringBoot{

	@RequestMapping("/hello")
	@ResponseBody
	public Map<String, Object> sayhello(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "Hello Spring Boot!");
		return map;
	}
}

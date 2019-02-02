package com.yang.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yang.entity.Area;
import com.yang.service.AreaService;

@Controller
@RequestMapping("superadmin")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/listare", method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object>listArea(){
		Map<String, Object>modelMap = new HashMap<>();
		List<Area>list = new ArrayList<>();
		try {
			list = areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("erroMsg", e.toString());
		}
		return modelMap;
	}
}

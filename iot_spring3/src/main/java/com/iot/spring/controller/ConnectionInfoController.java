package com.iot.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.UserInfoVO;

@Controller
@RequestMapping("/connection")
public class ConnectionInfoController {
	 private ObjectMapper om = new ObjectMapper();//변환시켜주는것 Gsonr같은거라고 생각하자
	@Autowired
	ConnectionInfoService cis;
	ConnectionInfoVO ci = new ConnectionInfoVO();
	
	@RequestMapping("/list")
	public @ResponseBody Map<String,Object> getConnectionList(HttpSession hs,Map<String,Object> map){
		UserInfoVO ui=new UserInfoVO();
		if(hs.getAttribute("user")!=null) {
			ui=(UserInfoVO)hs.getAttribute("user");
		}else {
			ui.setUiId("red");
		}
		
		ci.setUiId(ui.getUiId());
		map.put("list", cis.getList(ci));
		
		return map;
	}
	@RequestMapping(value="/db_list/{ciNo}",method=RequestMethod.GET)
	public@ResponseBody Map<String,Object> getdb_list(@PathVariable("ciNo") int ciNo,Map<String,Object> map,HttpSession hs){
		

		  List<Map<String, Object>> list;
	      try {
	         list = cis.getDbList(hs,ciNo);
	         map.put("list", list);
	         map.put("parentId", ciNo);
	      }catch(Exception e){
	         map.put("error", e.getMessage());
	         
	      }
		
		return map;
	}
	@RequestMapping("/insert")
	public @ResponseBody Map<String,Object> getinsert(@RequestParam Map<String,Object> map){
		ci=om.convertValue(map,ConnectionInfoVO.class);
		cis.insertConnection(map, ci);
		System.out.println(map);
		return map;
	}

	
	
}

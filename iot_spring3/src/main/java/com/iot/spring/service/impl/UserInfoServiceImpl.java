package com.iot.spring.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.dao.UserInfoDAO;
import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDAO uidao;
	
	@Override
	public boolean login(Map<String, Object> rMap,UserInfoVO ui) {
		ui=uidao.selectUserInfo(ui);
		rMap.put("msg","아디,비번 확인요");
		rMap.put("biz",false);
		if(ui!=null) {
			rMap.put("msg",ui.getUiName()+"님 잘왔어여");
			rMap.put("biz",true);
			rMap.put("user",ui);
			return true;			
		}
		return false;
	}

	@Override
	public int join(UserInfoVO ui) {
		String pwd=ui.getUiPwd();
		
		ui.setUiPwd(null);
		if(uidao.selectUserInfo(ui)!=null) {
			return 2;
		}
		ui.setUiPwd(pwd);
		return uidao.insertUserInfo(ui);
	}

}

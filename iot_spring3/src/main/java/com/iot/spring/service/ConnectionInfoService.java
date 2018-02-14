package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.UserInfoVO;

public interface ConnectionInfoService {

	public List<ConnectionInfoVO> getList(ConnectionInfoVO ui);
	public List<Map<String, Object>> getDbList(HttpSession hs,int ci)throws Exception;
	public void insertConnection(Map<String,Object> map,ConnectionInfoVO ci);

}

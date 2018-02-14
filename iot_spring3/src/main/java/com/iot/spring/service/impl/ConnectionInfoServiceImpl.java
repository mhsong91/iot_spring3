package com.iot.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.common.dbcon.DBConnector;
import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ConnectionInfoVO;


@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService{

	@Autowired
	ConnectionInfoDAO cdao;
	@Override
	public List<ConnectionInfoVO> getList(ConnectionInfoVO ui) {
		
		return cdao.selectList(ui);
	}
	@Override
	public List<Map<String,Object>> getDbList(HttpSession hs,int ciNo) throws Exception {
		
		ConnectionInfoVO ci=cdao.selectDbList(ciNo);
		DBConnector dbc=new DBConnector(ci);
		SqlSession ss=dbc.getSqlSession();
		hs.setAttribute("sqlsession",ss);
		List<Map<String,Object>> list=cdao.selectDatabaseList(ss);
		int idx=0;
		for(Map<String,Object> db:list) {
			db.put("id",ciNo+"_"+(++idx));
			db.put("text",db.get("Database"));
			db.put("items",new Object[] {});
		}
		return list;
		
		
	}
	@Override
	public void insertConnection(Map<String, Object> map, ConnectionInfoVO ci) {
		int result=cdao.insertConnection(ci);
		if(result==1) {
			map.put("msg","삽입성공");
		}else {
			map.put("msg","삽입실패");
		}
		
	}

}

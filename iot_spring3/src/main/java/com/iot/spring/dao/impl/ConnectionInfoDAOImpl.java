package com.iot.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.vo.ConnectionInfoVO;


@Repository
public class ConnectionInfoDAOImpl implements ConnectionInfoDAO{

	@Autowired
	private SqlSessionFactory ssf;
	

	@Override
	public List<ConnectionInfoVO> selectList(ConnectionInfoVO ui) {
		SqlSession ss=ssf.openSession();
		List<ConnectionInfoVO> list;
		list=ss.selectList("Connection.selectConnection",ui);
		ss.close();
		return list;
	}


	@Override
	public ConnectionInfoVO selectDbList(int ciNo) {
		SqlSession ss=ssf.openSession();
		
		ConnectionInfoVO ci =ss.selectOne("Connection.selectConnectionWithCiNo",ciNo);
		ss.close();
		return ci;
	}


	@Override
	public List<Map<String, Object>> selectDatabaseList(SqlSession ss)throws Exception {
		
		 return ss.selectList("Connection.selectDatabase");
	}


	@Override
	public int insertConnection(ConnectionInfoVO ci) {
		SqlSession ss=ssf.openSession();
		int result=ss.insert("Connection.insertConnection",ci);
		ss.close();
		return result;
	}




}

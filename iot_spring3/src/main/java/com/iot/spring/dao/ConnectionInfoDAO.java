package com.iot.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.iot.spring.vo.ConnectionInfoVO;

public interface ConnectionInfoDAO {

	public List<ConnectionInfoVO> selectList(ConnectionInfoVO ui);
	public ConnectionInfoVO selectDbList(int ci);
	public List<Map<String, Object>> selectDatabaseList(SqlSession ss)throws Exception;
	public int insertConnection(ConnectionInfoVO ci);
}

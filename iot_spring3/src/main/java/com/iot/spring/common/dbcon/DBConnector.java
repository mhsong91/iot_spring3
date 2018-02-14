package com.iot.spring.common.dbcon;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;

import com.iot.spring.vo.ConnectionInfoVO;

public class DBConnector {
   private BasicDataSource bds;
   private SqlSessionFactoryBean ssf;
   private SqlSession ss;
   
   
   public DBConnector(ConnectionInfoVO ci) {
      bds = new BasicDataSource();
      bds.setDriverClassName("org.mariadb.jdbc.Driver");
      String url = "jdbc:mysql://"+ci.getCiUrl() +":"+ci.getCiPort();
      bds.setUrl(url);
      bds.setUsername(ci.getCiUser());
      bds.setPassword(ci.getCiPwd());
      ssf = new SqlSessionFactoryBean();
      ssf.setDataSource(bds);
      ssf.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
   }
   
  
   public SqlSession getSqlSession() throws Exception {
	   return ssf.getObject().openSession();
   }

//   public static void main(String[] args) {
//	 
//      ConnectionInfoVO cvo = new ConnectionInfoVO();
//      cvo.setCiUrl("localhost");
//      cvo.setCiPort(3306);
//      cvo.setCiUser("root");
//      cvo.setCiPwd("test");
//      DBConnector dbc = new DBConnector(cvo);
//      Connection con = dbc.getConnection();
//      if(con!=null) {
//         System.out.println("연결성공");
//      }
//   }
   
   
}
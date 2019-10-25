package com.weovercome;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot1ApplicationTests {

	// 인젝트하면 ~수도 있기 때문에 
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private SqlSessionFactory sessionfactory;
	
	@Test
	public void contextLoads() {
	
	}
	
	@Test
	public void connectionTest() {
		System.out.println(datasource); //자동으로 객체 만들어지나 테스트해보자 우린 의심이 많잖아여!
		Connection conn;
		try {
			conn = datasource.getConnection();
			System.out.println(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSqlSessionFactory() {
		System.out.println("체크체크 check SessoinFactory : "+sessionfactory);
	}
	//이 파일에서 오른클릭해서 런에즈 junit테스트로 들어가야함
}

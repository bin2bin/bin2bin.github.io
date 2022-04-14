package com.uni.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
		public static Connection getConnection() {

			Connection conn = null;
			
			//Properties 파일에 접근할 수 있는 prop 객체 생성
			Properties prop = new Properties();

			String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();//getPath() : File에 입력된 경로 리턴

			 System.out.println(fileName);

			try {
				//파일 경로를 fileName 변수에 넣어놔서 그걸 가져옴
				prop.load(new FileReader(fileName));

				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("username");
				String password = prop.getProperty("password");

				// 1. 클래스 객체 등록, Driver 등록
				Class.forName(driver);

				// 2. DBMS와 연결 
				conn = DriverManager.getConnection(url, user, password);
				
				//자동커밋 꺼주기 -> 쿼리 결과에 따라 커밋을 할지말지 결정할것이기에
				conn.setAutoCommit(false);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return conn;

		}

		public static void close(Connection conn) {
			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void close(Statement stmt) {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void close(ResultSet rs) {
			try {
				if (rs != null && !rs.isAfterLast())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void commit(Connection conn) {
			try {
				if (conn != null && !conn.isClosed())
					conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void rollback(Connection conn) {
			try {
				if (conn != null && !conn.isClosed())
					conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}

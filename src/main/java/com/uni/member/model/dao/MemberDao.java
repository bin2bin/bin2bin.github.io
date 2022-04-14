package com.uni.member.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.uni.member.model.dto.Member;
import com.uni.notice.model.dto.Notice;

public class MemberDao {
	private Properties prop = new Properties();

	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath(); //패키지경로까지 가져올 수 있다
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Member loginMember(Connection conn, String userId, String userPwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//loginMember=SELECT * FROM MEMBER WHERE USER_ID=? AND USER_PWD=? AND STATUS='Y'
		
		
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userId);
			pstmt.setString(2,  userPwd);
			
			
			rset = pstmt.executeQuery();
		
			if(rset.next()) { //값이 담겨있는 경우에만
				loginUser = new Member( rset.getInt("USER_NO"),
										rset.getString("USER_ID"),
										rset.getString("USER_PWD"),
										rset.getString("USER_NAME"),
										rset.getString("PHONE"),
										rset.getString("EMAIL"),
										rset.getString("ADDRESS"),
										rset.getString("INTEREST"),
										rset.getDate("ENROLL_DATE"),
										rset.getDate("MODIFY_DATE"),
										rset.getString("STATUS")
														);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset); //방문을 열고 들어갔을때 안쪽 방문 부터 닫아줘야되는것처럼 뒤에 실행한거 부터 닫아준다  
			close(pstmt);
		}

		
		return loginUser;
	}

	public int insertMember(Connection conn, Member updatemem) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		//insertMember=INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, DEFAULT)
		
		String sql = prop.getProperty("insertMember");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatemem.getUserId());
			pstmt.setString(2, updatemem.getUserPwd());
			pstmt.setString(3, updatemem.getUserName());
			pstmt.setString(4, updatemem.getPhone());
			pstmt.setString(5, updatemem.getEmail());
			pstmt.setString(6, updatemem.getAddress());
			pstmt.setString(7, updatemem.getInterest());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		Member mem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				mem = new Member( rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("INTEREST"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("MODIFY_DATE"),
						rset.getString("STATUS")
										);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset); //방문을 열고 들어갔을때 안쪽 방문 부터 닫아줘야되는것처럼 뒤에 실행한거 부터 닫아준다  
			close(pstmt);
		}
		
		return mem;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserName()); //쿼리문에 인덱스 순서와 같아야한다...
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updatePwd(Connection conn, String userId, String userPwd, String newPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}



}

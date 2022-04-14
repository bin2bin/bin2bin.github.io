package com.uni.notice.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.notice.model.dto.Notice;

public class NoticeDao {
	private Properties prop = new Properties();

	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();
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

	public ArrayList<Notice> selectList(Connection conn) {
		
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;  //결과값 받아올 ResultSet
		//selectList=SELECT NOTICE_NO, NOTICE_TITLE, USER_ID, COUNT, CREATE_DATE FROM NOTICE N JOIN MEMBER ON (NOTICE_WRITER=USER_NO) WHERE N.STATUS='Y' ORDER BY NOTICE_NO DESC
		
		String sql = prop.getProperty("selectList");//키 값으로 읽어옴
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {//결과값이 있으면 
				list.add(new Notice(rset.getInt("NOTICE_NO"), //새로 생성하면서 값이 있으면 add
									rset.getString("NOTICE_TITLE"),
									rset.getString("USER_ID"),
									rset.getInt("COUNT"),
									rset.getDate("CREATE_DATE")
									
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset); //인식이 안되면 import static com.uni.common.JDBCTemplate.*; Dao에 임포트 해주기
			close(pstmt);
		}
		return list;
	}
	
	
	public int insertNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		//insertNotice=INSERT INTO NOTICE VALUES(SEQ_NNO.NEXTVAL, ?, ?, ?, DEFAULT, SYSDATE, DEFAULT)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()) );//DB에는 작성자의 데이터타임이 NUMBER로 되어있기 때문에 형변환 (Integer.parseInt) 해주어서 insert 해주어야한다.
				
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	public int increaseCount(Connection conn, int nno) {

	      //결과
	      int result = 0;
	      PreparedStatement pstmt = null;
	      
	      //increaseCount=UPDATE NOTICE SET COUNT = COUNT+1 WHERE NOTICE_NO=? AND STATUS='Y'
	      String sql = prop.getProperty("increaseCount");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         //쿼리 값 셋팅 
	         pstmt.setInt(1, nno);
	         
	         result = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
	   }


	

	 public Notice selectNotice(Connection conn, int nno) {
	      
	      //조회를 못해올 수도 있으므로 null로 초기값 선언
	      Notice n = null;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      //selectNotice=SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, USER_ID, COUNT, CREATE_DATE 
	      //FROM NOTICE N JOIN MEMBER ON (NOTICE_WRITER=USER_NO) WHERE NOTICE_NO=? AND N.STATUS='Y'
	      String sql = prop.getProperty("selectNotice");

	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         //쿼리 값 셋팅
	         pstmt.setInt(1, nno);
	         
	         rset = pstmt.executeQuery(); //ResultSet 조회 결과를 받아올때는 executeQuery를 사용한다.
	         
	         if(rset.next()) { //결과는 하나만 담기기 때문에 반복문 필요없음
	            
	            //객체 생성 => 매개변수 생성자 이용
	            n = new Notice(rset.getInt("NOTICE_NO"),
	                        rset.getString("NOTICE_TITLE"),
	                        rset.getString("NOTICE_CONTENT"),
	                        rset.getString("USER_ID"),
	                        rset.getInt("COUNT"),
	                        rset.getDate("CREATE_DATE")
	                        );
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return n;
	   }

	public int deleteNotice(Connection conn, int nno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		//deleteNotice=UPDATE NOTICE SET STATUS='N' WHERE NOTICE_NO=?
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateNotice(Connection conn, String title, String content, int nno) {
			int result = 0;
			
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateNotice");
			//updateNotice=UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=? WHERE NOTICE_NO=?
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title); //jsp에서 넘겨줬던 겟 파라미터로 받은 값 
				pstmt.setString(2, content);
				pstmt.setInt(3, nno);
					
				result = pstmt.executeUpdate();// 인설트, 업데이트, 딜리트를 사용할때는 executeUpdate 사용, DB 자체에 행이 바뀌기 때문에 executeUpdate를 사용
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}


	

}

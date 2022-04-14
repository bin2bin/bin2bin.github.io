package com.uni.board.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.board.model.dto.Attachment;
import com.uni.board.model.dto.Board;
import com.uni.board.model.dto.PageInfo;

import static com.uni.common.JDBCTemplate.*;

public class BoardDao {
	private Properties prop = new Properties();
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
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


	public int getListCount(Connection conn) {
		int listCount = 0;
		
		//getListCount=SELECT COUNT(*) FROM BOARD WHERE BOARD_TYPE=1 AND STATUS='Y'
		Statement stmt = null; //물음표가없는게 프리페어드랑 다름
		ResultSet rset = null;
		
		String sql = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement(); //시간이 쫌 더 걸리더라도 직접 쳐보는 연습 / 쿼리문도 꼭 이해하고 넘어가기
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {//( 카운트, 썸, 에버리지 = 하나만 값이 나오기에 if문 사용 )
				listCount = rset.getInt(1); //딱 하나만 나오기에 한 행만 나오기에 1번째 컬럼만 가져오겠다는 뜻 //COUNT(*) 집계함수 숫자하나만 나옴  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}


	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<Board> list = new ArrayList<Board>(); //반환해줄 객체를 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		//selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT BOARD_NO, CATEGORY_NAME, BOARD_TITLE, USER_ID, COUNT, CREATE_DATE FROM BOARD B JOIN CATEGORY USING(CATEGORY_NO) JOIN MEMBER ON (BOARD_WRITER=USER_NO) WHERE BOARD_TYPE=1 AND B.STATUS='Y' ORDER BY BOARD_NO DESC) A) WHERE RNUM BETWEEN ? AND ?		
		
		//board 게시글의 입장으로 생각
		/*	currentPage = 1 startRow = 1 endRow = 10;
		 * currentPage = 2 startRow = 11 endRow = 20;
		 * currentPage = 3 startRow = 21 endRow = 30;
		 * 
		 * 
		 * 
		 * */
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1; //물음표에 값을 넣어주기 위해 구해준다 pi에서 다 받아온거에서 가져오는거임 담겨있는걸 쓰는거임
		int endRow = startRow + pi.getBoardLimit() -1;
		//페이지의 시작수와 끝수를 계속 값을 가져오기 위해서 작성을 해줌
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			//BOARD_NO, CATEGORY_NAME, BOARD_TITLE, USER_ID, COUNT, CREATE_DATE 조회되는 컬럼만
			rset = pstmt.executeQuery();
			while(rset.next()) {
				//
				list.add(new Board(rset.getInt("BOARD_NO"), //객체들을 추가해주는거임 new 보드로 객체를 생성해서 넣어준다
						           rset.getString("CATEGORY_NAME"),
						           rset.getString("BOARD_TITLE"),
						           rset.getString("USER_ID"),
						           rset.getInt("COUNT"),
						           rset.getDate("CREATE_DATE")  //게시판에 6개만 보여주기 때문에
						           ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int insertBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		//insertBoard=INSERT INTO BOARD VALUES(SEQ_BNO.NEXTVAL, 1, ?, ?, ?, ?, DEFAULT, SYSDATE, DEFAULT)
		
		String sql = prop.getProperty("insertBoard");
		
		/*CATEGORY_NO	NUMBER
		BOARD_TITLE	VARCHAR2(100 BYTE)
		BOARD_CONTENT	VARCHAR2(4000 BYTE)*/
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		/*BOARD_NO
		BOARD_TYPE 여기까지 있으니
		CATEGORY_NO 이거
		BOARD_TITLE 이거
		BOARD_CONTENT 이거
		BOARD_WRITER 이거 이렇게 물음표순서대로 넣어줌,,,
		COUNT
		CREATE_DATE
		STATUS*/
		
		return result;
	}


		public int insertAttachment(Connection conn, Attachment at) {
		// insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, SEQ_BNO.CURRVAL, ?, ?, ?, SYSDATE, NULL, DEFAULT)
		int result = 0;
			
		/*ORIGIN_NAME
		CHANGE_NAME
		FILE_PATH*/
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


		public int increaseCount(Connection conn, int bno) { //조회수 증가
			//increaseCount=UPDATE BOARD SET COUNT=COUNT+1 WHERE BOARD_NO=?
				
			int result = 0; //쿼리 실행 결과를 담을 변수 못담을수도 있으니 0을 선언
			PreparedStatement pstmt = null; //쿼리문에 파일을 실행시키기 위해 null값 선언
			
			String sql = prop.getProperty("increaseCount"); //쿼리문이 적힌 파일을 읽어오며, 키값을 적어준다.
			try {
				pstmt = conn.prepareStatement(sql); //쿼리문이 적힌 파일을 sql 변수를 통해 실행한다.
				pstmt.setInt(1, bno); //가지고 온 bno는 숫자이니 Int형을 적어준다. WHERE절에 필요함 
				
				result = pstmt.executeUpdate(); //INSERT, UPDATE, DELETE 쿼리를 실행할 때 사용되며, 실행결과 변경된 레코드의 개수를 리턴한다.
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt); //PreparedStatement를 다시 닫아준다.
			}
			return result; //결과를 반환
		}


		public Board selectBoard(Connection conn, int bno) { 
	/*		selectBoard=SELECT BOARD_NO, CATEGORY_NAME, BOARD_TITLE, BOARD_CONTENT, USER_ID, COUNT, CREATE_DATE 
			FROM BOARD B JOIN MEMBER ON(BOARD_WRITER = USER_NO) LEFT JOIN CATEGORY USING(CATEGORY_NO) 
			WHERE B.STATUS = 'Y' AND BOARD_NO=?
			
			//쿼리문에서 위에 상태값이 Y인 게시물에 NO를 넣으면 결과가 조회된다.
																															*/
			Board b = null; //Board에 값을 담아서 리턴, 없을 수도 있으니 null을 선언
			PreparedStatement pstmt = null; //쿼리문에 파일을 실행시키기 위해 null값 선언
			ResultSet rset = null; //쿼리문 결과를 받아줄 ResultSet null값 선언
			
			String sql = prop.getProperty("selectBoard"); //쿼리문이 적힌 파일을 읽어오며, 키값을 적어준다.
				
			try {
				pstmt = conn.prepareStatement(sql); //쿼리문이 적힌 파일을 sql 변수를 통해 실행한다.
				pstmt.setInt(1, bno); //쿼리문 where절에 들어갈 bno를 set해준다. 서블릿부터 받아온 특정 게시물을 쿼리문에 넣어야하기 때문에
				
				rset = pstmt.executeQuery();//SELECT 쿼리를 실행할 때 결과 집합을 담아서 값이 있는치 체크 후 담아줌 반환해줄 결과 b를 만듬
				
				if(rset.next()) { //게시글 상세보기를 실행하여 1행이 담겨 있으므로 while이 아닌 if를 써준다.
					
					//BOARD에 있는 내용을 BOARD_TYPE 제외하고 7개를 가져와줄거임 
					
				  /*BOARD_NO
					BOARD_TYPE 제외 이유 : 게시글타입(일반1/사진2) 등 클라이언트에게 보여질 정보가 아닌 DB 관리자가 게시글 타입을 구분하기 위해 만든 컬럼이기 때문
					CATEGORY_NO
					BOARD_TITLE
					BOARD_CONTENT
					BOARD_WRITER 대체 이유 : 작성자 회원 번호는 클라이언트가 아닌 DB 관리자가 확인하기 위한 용도이므로 쿼리문에는 USER_ID로 대체하여 클라이언트에게 보여줌
					COUNT
					CREATE_DATE */
					
					//쿼리문하고 비교하면서 보기 쿼리문에 값들을 넣어주는거다보니 7개만 받아와서 넣어준다
					
					/*public Board(int boardNo, String category, String boardTitle, 
					 * String boardContent, String boardWriter, int count, Date createDate) */
					b = new Board(rset.getInt("BOARD_NO"),  //매개변수 생성자 순서대로 get을 해오면된다
					           rset.getString("CATEGORY_NAME"),
					           rset.getString("BOARD_TITLE"),
					           rset.getString("BOARD_CONTENT"),
					           rset.getString("USER_ID"), //객체 생성자에는 boardWriter로 명시되어있으나, 쿼리문에는 이자리에 USER_ID로
					           							 //적혀있기에 해당 자리에 USER_ID를 적어줌 (작성자 회원번호 컬럼이어서 클라이언트가 볼 필요 없음)			
					           rset.getInt("COUNT"),
					           rset.getDate("CREATE_DATE")  
					           );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset); //열어줬던 ResultSet 닫아주고
				close(pstmt);//PreparedStatement 도 닫아준다.
				
				//열어줬던 순서의 거꾸로 닫는 이유 = 방문을 들어갔다고 생각했을때 안쪽부터 문을 닫고 온다고 이해하면 편함
			}
			return b; //객체 안에 쿼리 실행문을 담아서 리턴, 안담겼다면 null을 리턴
		}


		public Attachment selectAttachment(Connection conn, int bno) {
			//selectAttachment=SELECT FILE_NO, ORIGIN_NAME, CHANGE_NAME FROM ATTACHMENT WHERE REF_BNO=? AND STATUS='Y'
			Attachment at = null; //Attachment에 값을 담아서 리턴, 없을 수도 있으니 null을 선언
			PreparedStatement pstmt = null; //쿼리문에 파일을 실행시키기 위해 null값 선언
			ResultSet rset = null; //쿼리문 결과를 받아줄 ResultSet null값 선언
			
			String sql = prop.getProperty("selectAttachment"); //쿼리문이 적힌 파일을 읽어오며, 키값을 적어준다.
			
			try {
				pstmt = conn.prepareStatement(sql);//쿼리문이 적힌 파일을 sql 변수를 통해 실행한다.
				pstmt.setInt(1, bno);//쿼리문 where절에 들어갈 bno를 set해준다. 서블릿부터 받아온 특정 게시물을 쿼리문에 넣어야하기 때문
				
				rset = pstmt.executeQuery();//SELECT 쿼리를 실행할 때 사용되며 ResultSet을 결과값으로 리턴한다.
				
				if(rset.next()) { //1개의 첨부파일만 담겨 있으므로 while이 아닌 if를 써준다.
					at = new Attachment(); //현재 만들어진 생성자가 없으니 여기서 바로 객체 생성자를 만들어준다
					
					//현재 필요한 생성자에 맞게 Attachment 객체에 SELECT절에 적힌 파일 번호, 원본, 수정을 순서에 맞게 생성 해준다
					at.setFileNo(rset.getInt("FILE_NO")); //파일 번호
					at.setOriginName(rset.getString("ORIGIN_NAME")); //파일 원본명
					at.setChangeName(rset.getString("CHANGE_NAME")); //파일 수정명
					
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset); //ResultSet을 다시 닫아주고
				close(pstmt);//PreparedStatement도 다시 닫아준다.
			}
			
			return at;  //객체 안에 쿼리 실행문을 담아서 리턴, 안담겼다면 null을 리턴
		}


		public int deleteBoard(Connection conn, int bid) {
			//deleteBoard=UPDATE BOARD SET STATUS='N' WHERE BOARD_NO=?
					
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteBoard");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bid);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		

		}


		public int deleteAttachment(Connection conn, int bid) {
			// deleteAttachment=UPDATE ATTACHMENT SET STATUS='N' WHERE REF_BNO=?
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteAttachment");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bid);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		

		}


		public int updateBoard(Connection conn, Board b) {
			//updateBoard=UPDATE BOARD SET CATEGORY_NO=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateBoard");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(b.getCategory()));
				pstmt.setString(2, b.getBoardTitle());
				pstmt.setString(3, b.getBoardContent());
				pstmt.setInt(4, b.getBoardNo());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}


		public int updateAttachment(Connection conn, Attachment at) {
			//	updateAttachment=UPDATE ATTACHMENT SET CHANGE_NAME=?, ORIGIN_NAME=?, FILE_PATH=? WHERE FILE_NO=?
			

			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getChangeName());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileNo());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}



		public int insertNewAttachment(Connection conn, Attachment at) {
			// insertNewAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, ?, SYSDATE, 1, DEFAULT)
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertNewAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, at.getRefBoardNo());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				
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

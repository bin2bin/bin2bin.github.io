package com.uni.board.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.board.model.dao.BoardDao;
import com.uni.board.model.dto.Attachment;
import com.uni.board.model.dto.Board;
import com.uni.board.model.dto.PageInfo;

public class BoardService {

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		return list;
	}

	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		int result1 = new BoardDao().insertBoard(conn, b); //게시글등록
		
		int result2 = 1;//첨부파일이 있을수도 없을수도여서 첨부파일이 없을때는 항상 1로 선언 이게 0이면  result1이 정상적으로 됐다해도 성공한게 아니어서 1을 선언
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) { //어떤수든 곱하면 0이니까
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}


	public Board selectBoard(int bno) {
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, bno); //조회수 올려지는 내용이 들어있음
		Board b = null;
		if(result > 0 ) {
			commit(conn);
			b = new BoardDao().selectBoard(conn, bno);
		}else {
			rollback(conn);
		}
		close(conn);
		return b;
	}
	
	public Attachment selectAttachment(int bno) {
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, bno);
		close(conn);
		return at;
	}

	public Board selectupdateBoard(int bno) {
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectBoard(conn, bno);
		
		close(conn);
		
		return b;
	}

	public int deleteBoard(int bid) {
		Connection conn = getConnection();
		
		//조회해서 결과 있으면 담는다
		int result1 = new BoardDao().deleteBoard(conn, bid);
		int result2 = 1;
		Attachment at = new BoardDao().selectAttachment(conn, bid);
		if(at != null) {
			result2 = new BoardDao().deleteAttachment(conn, bid);
		}
		
		if(result1 > 0 && result2 > 0 ) { 
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}

	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		
		int result2 = 1;
		
		if(at != null) {
			if(at.getFileNo() != 0) {
				result2 = new BoardDao().updateAttachment(conn, at);
			}else {
				result2 = new BoardDao().insertNewAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0 ) { 
			commit(conn);
		}else {
			System.out.println("service at -----------" + at);
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	
	}





}

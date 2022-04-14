package com.uni.notice.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.member.model.dao.MemberDao;
import com.uni.member.model.dto.Member;
import com.uni.notice.model.dao.NoticeDao;
import com.uni.notice.model.dto.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(conn); //조회 건수가 1건이아니라 여러건으로 가져오기 때문에 ArrayList<Notice>로 받아줌
		
		close(conn);
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	
		return result;
	}
	


	public Notice selectNotice(int nno) {
		
		Connection conn = getConnection();
		int result = new NoticeDao().increaseCount(conn, nno); //조회수를 올리는 메소드 
		
		//게시글 조회를 위한 객체
		Notice n = null;
		
		if(result > 0 ) {
			commit(conn);
			n = new NoticeDao().selectNotice(conn, nno);
		}else {
			rollback(conn);
		}
		close(conn);
		return n;
	}

	

	public int deleteNotice(int nno) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, nno);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return nno;
	}

	public int updateNotice(String title, String content, int nno) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, title, content, nno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



}

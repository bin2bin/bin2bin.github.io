package com.uni.member.model.service;

import static com.uni.common.JDBCTemplate.*;
import static com.uni.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.uni.member.model.dao.MemberDao;
import com.uni.member.model.dto.Member;


public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		Member loginUser = new MemberDao().loginMember(conn, userId, userPwd);
		
		close(conn);
		
		return loginUser;
	}

	public int insertMember(Member updatemem) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, updatemem);
		
		if(result > 0) { //넘어온 result 값이 0보다 크면
			commit(conn);
		}else {			//아니라면 롤백
			rollback(conn);
		}
		
		close(conn); //다 됐으면 커넥션 닫아주고
		return result; //result 리턴
	}

	public Member selectMember(String userId) {
		Connection conn = getConnection();
		
		Member mem = new MemberDao().selectMember(conn, userId);
		
		close(conn);
		return mem;
	}

	public Member updateMember(Member m) {
		Connection conn = getConnection();
		Member updateMem = null;
		int result = new MemberDao().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, m.getUserId()); //selectMember userId로 조회를 해왔어서 이거 쓰면 됨 그리고 업데이트 한 값을 담음
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		Member updateMem = null;
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member updatePwd(String userId, String userPwd, String newPwd) {
		Connection conn = getConnection();
		Member updateMem = null;
		int result = new MemberDao().updatePwd(conn, userId, userPwd, newPwd);
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId); //값이 담겨있다면 업데이트 멤버에 담아서 리턴
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}

	

}

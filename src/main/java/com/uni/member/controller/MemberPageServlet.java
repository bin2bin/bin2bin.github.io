package com.uni.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.member.model.dto.Member;
import com.uni.member.model.service.MemberService;

/**
 * Servlet implementation class MemberPageServlet
 */
@WebServlet("/mypageMember.do")
public class MemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser"); //일단 세션에 있는 로그인 유저를 받아옴
		String userId = loginUser.getUserId(); //userid를 String에 담음
		
		Member member = new MemberService().selectMember(userId);
		
		System.out.println(member); //멤버 객체를 잘 받아오는지 찍는 용도
		
		RequestDispatcher view = null; //마이페이지에 담아서 다시 마이페이지로 화면 전환
		
		if(member != null) { //넘어온게 값이 있다면
			request.setAttribute("loginUser", member); //로그인 유저에 멤버를 담음
			view = request.getRequestDispatcher("views/member/myPage.jsp");
		}else { //넘어온게 값이 없다면 에러페이지로 이동
			request.setAttribute("msg", "조회실패하였습니다.");
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
		}
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

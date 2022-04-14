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
 * Servlet implementation class MemberinsertServlet
 */
@WebServlet("/insertMember.do")
public class MemberinsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberinsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); //jsp에서 값을 가져옴
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest");//체크박스라 여러개 넘어오니까 getParameterValues / String[] 배열로 변경 변수명도 interests
		
		String interest = "";
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		Member updateMem = new Member(userId, userPwd, userName, phone, email, address, interest); //멤버에 받아줄 생성자가 있으니 그대로 객체 생성해서 넘겨줌
		
		int result = new MemberService().insertMember(updateMem);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "회원가입성공");
			response.sendRedirect(request.getContextPath());
			
		}else {
			request.setAttribute("msg", "회원가입실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

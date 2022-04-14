package com.uni.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uni.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/deleteMember.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId"); //탈퇴를 하려면 userId값을 알아야하고 다 받을 필요없이 필요한것만 받으면됨
		
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0 ) {
			HttpSession session = request.getSession(); //세션 값을 가져 옴
			session.removeAttribute("loginUser"); //속성 지워주기
			session.setAttribute("msg", "회원탈퇴가 완료 되었습니다. 복구관련사항은 관리자에게 문의하세요");
			response.sendRedirect(request.getContextPath()); //탈퇴했으니 메인페이지로

		}else {
			request.setAttribute("msg", "회원탈퇴에 실패하였습니다.");
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

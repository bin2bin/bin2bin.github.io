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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/updateMember.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); //jsp에서 값을 가져옴
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest");//체크박스라 여러개 넘어오니까 getParameterValues / String[] 배열로 변경 변수명도 interests
		
		String interest = "";
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		
		
		Member updateMem = new MemberService().updateMember(new Member(userId, userName, phone, email, address, interest)); /*결과가 result가 아닌 updatemem 한 이유는 넘어와서 받아준다는 뜻*/
		
		if(updateMem != null) {
			request.getSession().setAttribute("msg", "성공적으로 회원정보를 수정하였습니다.");
			request.getSession().setAttribute("loginUser", updateMem); //set을 해야하기에 loginUser 속성에 바뀌게 된 updateMem를 셋팅해준다 
			response.sendRedirect(request.getContextPath());//메인으로
			
		}else {
			request.setAttribute("msg", "회원수정에 실패하였습니다.");
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

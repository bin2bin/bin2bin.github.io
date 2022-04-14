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
 * Servlet implementation class MemberUpdatePwdServlet
 */
@WebServlet("/updatePwdMember.do")
public class MemberUpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); /*ㅇbj 타입으로 넘어오기에 형변환 해줌 loginuser에서 id값을 얻어옴*/
		
		/*pwdUpdateForm.jsp에서  fnCheckPwd 함수로 비밀번호 체크를 하고 submit을 보냈기에 여기서 굳이 체크할 필요가없음*/
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd"); 
		String originPwd = (String) request.getAttribute("originPwd"); //로그인할때 오리지날패스워드값도 넣어줬기에 이것도 바꾸어줘야함 //변경해야함
		
	
		Member updateMem = new MemberService().updatePwd(userId, userPwd, newPwd);
		

		RequestDispatcher view = request.getRequestDispatcher("views/member/pwdUpdateForm.jsp"); //DB까지 갔다와서 다시 pwdUpdateForm.jsp로 보내줌

		
		if(updateMem != null) {
			request.setAttribute("sTag", "Y");
			request.setAttribute("msg",  "성공적으로 비밀번호를 변경하였습니다.");
			request.getSession().setAttribute("loginUser", updateMem); //비밀번호가 변경된 내용이 세션에도 들어가야함
			request.getSession().setAttribute("originPwd", originPwd); //로그인할때 오리지날패스워드값도 넣어줬기에 이것도 바꾸어줘야함
			//새로운 값을 세션에 넣어주는거임
		}else {
			request.setAttribute("msg", "비밀번호 변경에 실패였습니다.");
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

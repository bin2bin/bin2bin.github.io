package com.uni.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uni.notice.model.dto.Notice;
import com.uni.notice.model.service.NoticeService;

/**
 * Servlet implementation class updateFormNotice
 */
@WebServlet("/updateNotice.do")
public class updateNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		String title = request.getParameter("title"); //이미 내용이 담겨있음
		String content = request.getParameter("content"); //사용자가 보낸 내용이 담겨있음
		
		int result = new NoticeService().updateNotice(title, content.replaceAll("\n", "<br>"), nno); //여기 까지만 적어보고 다오에서 잘 가져왔는지 시스아웃으로 찍어봐도 된다.
		//replaceAll 앞에 인자를 바꿔준다
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글 수정 완료"); //세션에 정보를 가져와서 담아줘야한다.
			response.sendRedirect("detailNotice.do?nno="+nno); //sendRedirect는 단순히 경로, 이걸 해야 msg가 뜬다 sendRedirect = body부분만 가져옴
			
		}else {
			request.setAttribute("msg", "게시물 수정에 실패였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); //에러페이지를 해줘야 msg가 나온 
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)*/
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

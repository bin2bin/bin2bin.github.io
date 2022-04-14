package com.uni.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.member.model.dto.Member;
import com.uni.notice.model.dto.Notice;
import com.uni.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeInsertSevlet
 */
@WebServlet("/insertNotice.do")
public class NoticeInsertSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserNo());
		
		System.out.println("전 -------------------" + content);
		System.out.println("후 -------------------" + content.replaceAll("\n", "<br>"));
		
		Notice n = new Notice(title, writer, content.replaceAll("\n", "<br>"));
		

		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0 ) {
			//forward 방식은 URL 주소를 다 적어주고
			//sendRedirect 방식은 주소를 다 안적어줘도된다 / body 부분만 보낸다.
			
			request.getSession().setAttribute("msg", "공지사항이 등록 되었습니다.");  //getSession은 안써주면 메세지 창이 안뜬다.
			response.sendRedirect("listNotice.do"); //등록 후 공지사항 목록으로 이동
			
		}else {
			request.setAttribute("msg", "공지사항 등록 실패하였습니다.");
			
			//forward : 화면이동
			RequestDispatcher view =  request.getRequestDispatcher("views/common/errorPage.jsp"); //정보를 다 가지고 가니까 getSession을 써줄 필요가없음
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

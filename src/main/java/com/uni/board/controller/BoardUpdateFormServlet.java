package com.uni.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.board.model.dto.Attachment;
import com.uni.board.model.dto.Board;
import com.uni.board.model.service.BoardService;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/updateFormBoard.do")
public class BoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		Board b = new BoardService().selectupdateBoard(bno);
		Attachment at = new BoardService().selectAttachment(bno);
		
		if(b != null) {
			request.setAttribute("b", b); //null이 아니면 게시물 넘겨줌
			request.setAttribute("at", at); //null이 아니면 첨부파일 넘겨줌
			request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패하였습니다");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);

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

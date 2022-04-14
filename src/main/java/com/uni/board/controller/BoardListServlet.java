package com.uni.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.board.model.dto.Board;
import com.uni.board.model.dto.PageInfo;
import com.uni.board.model.service.BoardService;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/listBoard.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//페이징처리
		
		int listCount;   //총게시글 갯수
		int currentPage; //현재페이지 (요청한 페이지)
		int startPage;   //현재 페이지 하단에 보여지는 페이징 바의 시작수
		int endPage;     //현재 페이지 하단에 보여지는 페이징 바의 끝수
		
		int maxPage;     //전체 페이지의 가장 마지막 페이지
		int pageLimit;   //한페이지 하단에 보여질 페이지 최대갯수
		int boardLimit;  //한페이지에 보여질 게시글 최대갯수
		
		
		//총게시글 갯수
		listCount = new BoardService().getListCount();
		System.out.println("listCount : " + listCount);
		
		//현재페이지
		currentPage = 1; //게시판 눌렀을때 처음 보여지는 화면이 1페이지를 담아놔야 컴퓨터가 알아야기 때문에 다른 화면에서 눌러도 1페이지인걸 알게된다.  
		
		//페이지 전환시 전달받은 페이지가 있을경우 전달받은 페이지를 currentPage에 담기
		if(request.getParameter("currentPage") != null) { //null이 아니면 값이 담아져있다는거니 해당하는값을 다시 담아준다
			currentPage = Integer.parseInt(request.getParameter("currentPage")); //스트링 타입으로 넘어오기에 형변환
		}
		
		//페이지 최대갯수
		pageLimit = 10; //페이징바가 몇까지인지
		
		//게시글 최대갯수
		boardLimit = 10; //게시글 리스트
		
		// * maxPage : 총 페이지 수
				/*
				 * ex) boardLimit : 10 이라는 가정 하에
				 * 
				 * 총갯수   / boardLimit
				 * 100.0 / 10 = 10.0 = 10.0		=> 10페이지
				 * 101.0 / 10 = 10.1 = 11.0		=> 11페이지
				 * 105.0 / 10 = 10.5 = 11.0		=> 11페이지
				 * 109.0 / 10 = 10.9 = 11.0		=> 11페이지
				 * 
				 * 총게시글갯수(실수)/boardLimit의 값을 올림한 값!
				 */
				
				maxPage = (int)Math.ceil((double)listCount/boardLimit); //ceil = 올림 
				//boardLimit = 10이니까 이걸 올림 해줌
				//올림을 해줬기 때문에 다음 페이지로 넘어감 110.0 / 10 = 11.0 = 11 페이지 / 11.1 = 12페이지
				
				// * startPage : 현재 페이지에 보여지는 페이징 바의 시작 수
				/*
				 * ex) pageLimit : 10      ( 10에서 다음페이지를 누르면 11페이지 )
				 * 1, 11, 21, 31, ...			=> n * 10 + 1
				 * 
				 * currentPage = 1				=> 0 * 10 + 1
				 * currentPage = 5				=> 0 * 10 + 1
				 * currentPage = 10				=> 0 * 10 + 1
				 * 
				 * currentPage = 11				=> 1 * 10 + 1
				 * currentPage = 12				=> 1 * 10 + 1
				 * currentPage = 20				=> 1 * 10 + 1
				 * 
				 * currentPage = 1~10			=> n=0
				 * currentPage = 11~20			=> n=1
				 * 
				 * 								   n = (currentPage - 1) / pageLimit
				 */
				
				//페이징바가 1,5,10 이어도 0*10+1 무조건 시작페이지가 1인거이고 / 11,12,20 = n에다가 1을 넣어서 11페이지인거임 
				
				startPage = (currentPage - 1) / pageLimit * pageLimit +1;
				
				// * endPage : 현재 페이지에 보여지는 페이징 바의 끝 수
				// startPage : 1	=> endPage : 10
				// startPage : 11	=> endPage : 20
				
				endPage = startPage + pageLimit -1;
				
				if(maxPage < endPage) { //끝페이지를 무분별하게 만들어놓지 않기위해서
					endPage = maxPage;
				}
				
				PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit); 
				
				ArrayList<Board> list = new BoardService().selectList(pi);
				
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
				
				request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

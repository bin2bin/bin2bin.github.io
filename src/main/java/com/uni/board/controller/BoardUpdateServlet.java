package com.uni.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.uni.board.model.dto.Attachment;
import com.uni.board.model.dto.Board;
import com.uni.board.model.service.BoardService;
import com.uni.common.MyfileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/updateBoard.do")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) { //멀티파티 요청일때 처리할거임
			
			int maxSize = 10 * 1024 * 1024;
			
			//1_2. 전달된 파일을 저장할 서버의 폴더 경로
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
			//폴더의 경로를 잘 가지고 왔으면 저장
			String savePath = resources + "\\board_upfiles\\";
			
			System.out.println("savePath "+ savePath ); //경로 잘찍히는지 찍어보기
			  
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy()); //저장되는 파일을 어떤 방식으로 파일명으로 바꾸어서 저장할건지 짜여있음
			
			int bno = Integer.parseInt(multiRequest.getParameter("bno"));
			
			Board b = new Board(); //보드 객체 생성
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			b.setCategory(multiRequest.getParameter("category"));
			b.setBoardNo(bno);
			
			Attachment at = null;
			if(multiRequest.getOriginalFileName("upFile") != null) { //수정할때 새로 첨부하는 내용들에 대한거임
				String originName = multiRequest.getOriginalFileName("upFile");
				String changeName = multiRequest.getFilesystemName("upFile");
				
				at = new Attachment(); //Attachment 객체 생성
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				
				
				System.out.println("originName" + originName);
				
				if(multiRequest.getParameter("originFile") != null) {
					
					File deleteFile = new File(savePath + multiRequest.getParameter("originFile"));
					
					deleteFile.delete();
					
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				}else {
					at.setRefBoardNo(bno);  //해당하는 게시글에 첨부하려고 담아둠
				}
				
			}
			
			int result = new BoardService().updateBoard(b, at);
			if(result > 0 ) {
				response.sendRedirect("detailBoard.do?bno="+bno);
			}else {
				request.setAttribute("msg", "게시글 수정 실패 ");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);

			}
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

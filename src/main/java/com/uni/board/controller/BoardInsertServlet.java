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
import com.uni.member.model.dto.Member;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/insertBoard.do")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//멀티파티폼으로 잘된 경우에만 전송을 할거임
		if(ServletFileUpload.isMultipartContent(request)) {
			//// 1. 전송된 파일들을 처리할 작업 내용 (전송되는 파일의 용량 제한, 전달된 파일을 저장할 폴더 경로)
			
			// 1_1. 전송파일 용량 제한 (int maxSize)
//					: 10Mbyte로 제한  ([참고] cos.jar로 파일 업로드 시 최대 2기가(1.6)까지만 가능)
//			     	1Kbyte = 1024byte (킬로바이트)
//					1Mbyte = 1024Kbyte = 1024 * 1024 byte (메가바이트)
//					1Gbyte = 1024Mbyte = 1024 * 1024 * 1024 Byte (기가바이트)
//					10Mbyte = 10 * 1024 * 1024 byte
				
			int maxSize = 10 * 1024 * 1024;
			
			//1_2. 전달된 파일을 저장할 서버의 폴더 경로
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
			//폴더의 경로를 잘 가지고 왔으면 저장
			String savePath = resources + "\\board_upfiles\\";
			
			System.out.println("savePath "+ savePath ); //경로 잘찍히는지 찍어보기
			
			//new DefaultFileRenamePolicy  이 방식은 같은 파일로 했을때 인덱스 추가되서 저장됨 강아지1, 강아지2 좋지않은 방식 중복된 파일 저장할때 인덱스 번호만 부여되서
			//MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());  
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy()); //저장되는 파일을 어떤 방식으로 파일명으로 바꾸어서 저장할건지 짜여있음
			
			//System.out.println(multiRequest.getOriginalFileName("upfile"));
			//System.out.println(multiRequest.getFilesystemName("upfile"));
			
			String category = multiRequest.getParameter("category"); //multiRequest 첨부파일이 있을수도 없을수도 
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			
			int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo(); //세션에 담겨있는거에서 정보를 뽑음
			
			Board b = new Board(); //게시글 객체 생성 게시글에 정보는 꼭 가져가야함
			b.setCategory(category);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setBoardWriter(String.valueOf(userNo)); //board dto에 이렇게 있는 생성자가 없어서 여기서 직접 생성해준다, dto를 가서 생성 여기서 set으로 바로 값을 넣거나 
			
			//dto에 만약 있으면 new 보드에 (category, title, content, content)
			
			Attachment at = null; //첨부파일 객체 첨부파일을 넣을수도 안넣을수도 있어서
			if(multiRequest.getOriginalFileName("upfile") != null) {//multipart/form-data 넘어온걸 쓸때는 꼭 multiRequest를 쓴다
				String originName = multiRequest.getOriginalFileName("upfile"); //원본명
				String changeName = multiRequest.getFilesystemName("upfile"); //바꾼이름
				
				System.out.println("originName : "+ originName);
				System.out.println("changeName : "+ changeName);
				
				at = new Attachment(); //첨부파일이 있으면 객체 생성
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
			}
			
			int result = new BoardService().insertBoard(b, at);
			
			if(result > 0 ) { //등록성공
				request.getSession().setAttribute("msg", "게시글 등록 성공");
				response.sendRedirect("listBoard.do");
			}else { //등록실패
				if(at != null) {
					File failedFile = new File(savePath+ at.getChangeName());
					failedFile.delete();
				}
				
				request.setAttribute("msg", "게시글 등록 실패");
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

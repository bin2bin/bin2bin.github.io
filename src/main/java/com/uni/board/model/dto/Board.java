package com.uni.board.model.dto;

import java.sql.Date;

public class Board {
	
	private int boardNo;			// 게시글 고유 번호
	private int boardType;			// 게시글 타입(1.일반게시판, 2.사진게시판)
	private String category;		// 게시글 분류(10 ~ 20)
	private String boardTitle;		// 게시글 제목
	private String boardContent;	// 게시글 내용
	private String boardWriter;		// 게시글 작성자 (번호 또는 이름)
	private int count;				// 게시글 조회수
	private Date createDate;		// 게시글 작성일
	private String status;			// 게시글 상태값(Y, N)
	
	private String titleImg;		// 게시글의 타이틀 이미지 (실제 서버에 업로드되어있는 이름)
	
	
	public Board() {
		
	}

	

	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter,
			int count, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}



	public Board(int boardNo, String category, String boardTitle, String boardWriter, int count, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}



	public Board(int boardNo, int boardType, String category, String boardTitle, String boardContent, String boardWriter,
			int count, Date createDate, String status) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}


	


	public int getBoardType() {
		return boardType;
	}


	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	

	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTitleImg() {
		return titleImg;
	}
	
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}



	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", category=" + category + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + ", titleImg=" + titleImg + "]";
	}

	
	

}

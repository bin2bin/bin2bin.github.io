package com.uni.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyfileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) { //원본 파일을 받아와서
		
		String originName = originFile.getName(); // 원본 파일명이 담김
		// 수정명 : 파일업로드한시간(년월일시분초) + 10000~99999사이의 랜덤값 (5자리랜덤값) + 확장자
		
		// 원본명  	--> 수정명
		// aaa.jpg 	--> 2019120109065323456.jpg
		
		//파일 업로드한 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //포맷을 yyyyMMddHHmmss 이런 방식으로 하겠다.
		
		String currentTime = sdf.format(new Date()); //util로 임포트 sdf를 담아줌
		
		
		// 5자리 랜덤값
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		
		//확장자
		String ext = ""; //확장자명이 담김
		int dot = originName.lastIndexOf("."); //.이 위치한 인덱스값을 찾아서
		
		ext = originName.substring(dot); //.jpg가 담김
		
		String fileName = currentTime + ranNum + ext;
		
		File renameFile = new File(originFile.getParent(), fileName); //전달받은 파일을 변경된 파일명으로 객체를 생성해서 리턴 시켜줄거임
		//받아온 오리지날 파일을 새로 생성한 파일명으로 대체하겠다.
		
		
		return renameFile;
	}

}

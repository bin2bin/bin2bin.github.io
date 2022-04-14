package com.uni.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String key) {
		
		String value = "";
		
		if(key != null && (key.equals("userPwd")||key.equals("newPwd"))) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");//SHA-512 암호화 객체 이용
				
				
				byte[] bytes = super.getParameter(key).getBytes(Charset.forName("UTF-8"));// 전달받은 비밀번호를 바이트 배열로 받아주기 
				
				md.update(bytes);// md 객체에 변환한 바이트 배열을 전달해서 갱신
				
				value = Base64.getEncoder().encodeToString(md.digest());// java.util.Base64 인코더를 이용하여 암호화된 바이트 배열을 인코딩 과정을통해 문자열로 출력
			}catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
				
			}
		}else {
			value = super.getParameter(key);
		}
		
		
		return value;
	}

}

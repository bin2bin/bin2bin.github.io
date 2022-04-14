/*package com.uni.common.filter;

/*경유지라고도 함*/
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//카피해서 만들지 말기..!
/**
 * Servlet Filter implementation class EncodingFilter
 */
//@WebFilter("/*") /*주석 처리 한 이유는 web.xml에서 url-pattern을 줄거이기에 한거임 */   /*요청을 가려서 받는게 아닌 post 방식인것만 처리를 해줌*/
//public class EncryptFilter implements Filter { //오류가 안잡힘.... 이미 생성되어있어서 생기는 오류라고 하는데 톰캣, 프로젝트 클린 이클립스 재실행 해도 안됨  

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
        // TODO Auto-generated constructor stub
    }
    	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() { //3. 역할이 끝난 필터는 웹 컨테이너에 의해 해당 메소드를 호출하고 소멸
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {/*2.필터가 수행할때 구동되는 메소드 요청,응답 객체를 사용하여
	 																																일련의 작업을 수행한뒤 chain을 통해 여러개의 클래스를 이용*/
		System.out.println("==============인코딩 필터 동작 start ========================");//필터를 거쳐서 다시 서블릿으로감
		
		if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("post")) { 
			
			//ServletRequest 이어서 HttpServletRequest로 형변환을 해줌  //getMethod : myPage의 경우 Method에 get인지 post인지 적혀있음 / equalsIgnoreCase : 대소문자 구분하지않고 post인지 확인
			 
			System.out.println("===========post 방식이 요청됨 ==============");
			request.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response); //이걸 기준으로 위는 서블릿 가기전 아래는 다녀온 후 필터가 web.xml에가 다 실행되야 서블릿으로 감
		
		
		System.out.println("==============서블릿 동작하고 나서 실행 ========================");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException { /*1.웹 컨테이너가 필터를 호출할 경우 해당 메소드 호출되어 필터 객체를 생성하며 초기화*/
		// TODO Auto-generated method stub
	}

}

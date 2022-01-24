package mvc.controller.MemberController;

import mvc.model.vo.Member.Member;

public class MemberController  {

	private Member mem = new Member("admin", "1234", "홍길동", "901022-1562334", 163.0);

	public MemberController() {
		// TODO Auto-generated constructor stub
	}

	public int longin(String id, String pwd) {

		int result = 0; //로그인 성공 실패를 담아줄 변수 선언

		if (mem.getMemberId().equals(id) && mem.getMemberPwd().equals(pwd)) {

			result = 1; //로그인 성공

		}
		return result; // if 조건에 맞지 않는다면 로그인 실패

	}

	public Member readinfo() {

		return mem;
	}

	public int readAge() {
		String yearStr = mem.getCitizenNo().substring(0,2); //공백을 포함한다 substring 0: 시작위치 / substring 2: 0번 인덱스부터 1번인덱스까지 출력 
		int year = Integer.parseInt(yearStr); //Integer.parseInt = String 를 int로 바꾸는 함수 "90" -> 90
		
		int age = 0; // 나이를 받아줄 변수
		
		//100살 이상은 고려하지 않음 잘못 입력했을 경우가 있기에
		if(year > 22) { //1900년대생
			age = 2022 - (1900 + year) + 1;
			
			}else { //2000년대생
				age = 2022 - (2000 + year) + 1;
			}
		
		return age; //구한 나이값 리턴

	}
	

	public void updateHeight(double height) {
		
		mem.setHeight(height);
		
	}
}

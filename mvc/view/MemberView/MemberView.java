package mvc.view.MemberView;

import java.util.Scanner;

import mvc.controller.MemberController.MemberController;

public class MemberView {

	private Scanner sc = new Scanner(System.in);

	private MemberController mc = new MemberController();

	public MemberView() {
		// TODO Auto-generated constructor stub
	}

	public void mainMenu() {

		System.out.print("아이디를 입력하시오 : ");
		String id = sc.nextLine();

		System.out.print("비밀번호를 입력하시오 : ");
		String pwd = sc.nextLine();

		int result = mc.longin(id, pwd); // 사용자가 입력한 아이디와 비밀번호 controller에 전달

		if (result == 1) { // controller 에 보낸뒤 일치할 경우
			System.out.println("로그인 성공");
			System.out.println();

			while (true) {
				System.out.println("=== 회원 프로그램 ===");

				System.out.println("1. 내 정보 보기");

				System.out.println("2. 내 나이 조회하기");

				System.out.println("3. 키 수정하기");

				System.out.println("9. 프로그램 종료하기");

				System.out.println("메뉴 번호 선택 : ");
				int menu = sc.nextInt();
				
			switch(menu) {
			
			case 1:
				System.out.println(mc.readinfo().information());
				break;
			case 2:
				System.out.println(mc.readAge());
				break;
			case 3:
				updateHeight();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력해주세요");
				break;
			} //switch
			} //while
		}else {
			System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
		}
		
	}
	
	public void updateHeight() {
		
		System.out.print("수정할 키를 입력하시오 : ");
		double height = sc.nextDouble(); 
		
		mc.updateHeight(height);
	}
	
}

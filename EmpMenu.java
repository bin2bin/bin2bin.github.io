package com.hw3.view.EmpMenu;

import java.util.Scanner;

import com.hw3.model.vo.Employee.Employee;

public class EmpMenu {
	Scanner sc = new Scanner(System.in);

	public EmpMenu() {
		// TODO Auto-generated constructor stub
	}

	public void mainMenu() {

		Employee emp = null;

		while (true) {
			System.out.println("===== 사원 정보 관리 프로그램 =====");
			System.out.println("1. 새 사원 정보 입력");

			System.out.println("2. 사원 정보 수정");

			System.out.println("3. 사원 정보 삭제");

			System.out.println("4. 사원 정보 출력");

			System.out.println("9. 프로그램 종료");

			System.out.print("메뉴 번호 선택 : ");
			int menuNum = sc.nextInt();

			switch (menuNum) {

			case 1:
				emp = inputEmployee(); // null 값이었던 emp 객체에 inputEmployee()를 실행한 결과를 담는다.
				break;
			case 2:
				modifyEmployee(emp);
				break;
			case 3:
				emp = null; // inputEmployee, modifyEmployee 입력 된 모든 정모를 처음으로 초기화
			case 4:
				System.out.println(emp.information());
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력했습니다. 다시 입력해주세요");

			} // switch문
		} // while문

	}

	public Employee inputEmployee() {
		sc.nextLine(); //Line으로 안빼주면 이름 출력안됨
		System.out.print("이름 : ");
		String empName = sc.nextLine();
		
		System.out.print("부서 : ");
		String dept = sc.nextLine();
		
		System.out.print("직급 : ");
		String job = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("성별 : ");
		char gender = sc.nextLine().charAt(0);
		
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		
		System.out.print("보너스포인트 : ");
		double bonusPoint = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();

		Employee newEmp = new Employee(empName, dept, job, age, gender, salary, bonusPoint, phone, address);
		return newEmp; // 사용자에게 입력 받은 정보를 객체 생성 후 Employee 매개변수로 받아서 case문으로 던져줌 1번

	}

	public void modifyEmployee(Employee emp) { // emp <= Employee 호출

		while (true) {
			System.out.println("===== 사원 정보 수정 메뉴 =====");

			System.out.println("1. 이름 변경");

			System.out.println("2. 급여 변경");

			System.out.println("3. 부서 변경");

			System.out.println("4. 직급 변경");

			System.out.println("9. 이전 메뉴로");

			System.out.print("번호 입력 : ");
			int Change = sc.nextInt();

			switch (Change) {
			case 1:
				System.out.print("변경 할 이름 : ");
				String rename = sc.nextLine();

				emp.setEmpName(rename);
				break;

			case 2:
				System.out.print("변경 할 급여 : ");
				int reSalary = sc.nextInt();

				emp.setSalary(reSalary);
				break;

			case 3:
				System.out.print("변경 할 부서 : ");
				String reDept = sc.nextLine();
				sc.nextLine();

				emp.setDept(reDept);
				break;

			case 4:
				System.out.print("변경 할 직급 : ");
				String rejob = sc.nextLine();

				emp.setJob(rejob);
				break;

			case 9:
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력해주세요");
				break;
			}
		}
	}
}

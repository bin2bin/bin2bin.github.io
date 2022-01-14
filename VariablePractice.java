package com.gj.practice1.example;

import java.util.Scanner;

public class VariablePractice {

	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요");
		
		String name = sc.nextLine();
		
		System.out.println("나이를 입력하세요");
		
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("성별을 입력하세요(남/여)");
		
		char gender = sc.nextLine().charAt(0);
	
		System.out.println("키를 입력하세요(cm)");
		
		double height = sc.nextDouble();
		
		System.out.println("키 "+ height+"인 " + age+ "살 " + gender + name+ "님 "+ "반갑습니다^^ " );
	}

	public void method2() {
		
		int a = 23;
		int b = 7;
		
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b);
	}
	
	public void method3() {
		
		double a = 13.5;
		double b = 41.7;
		
		System.out.println(a*b);
		System.out.println((a+b)*2);
	}
	
	public void method4() {
		Scanner sc = new Scanner(System.in);
		
	String a = "apple";
	
	System.out.println(a.charAt(0));
	System.out.println(a.charAt(1));
	System.out.println(a.charAt(2));
		
	}
	
	
	public void method5() {
		Scanner sc = new Scanner(System.in);
		System.out.println("국어점수 : ");
		int k = sc.nextInt();
		
		System.out.println("영어점수 : ");
		double e = sc.nextDouble();
		
		System.out.println("수학점수 : ");
		int m = sc.nextInt();
		
		double ave = k/e/m;
	}
	
}

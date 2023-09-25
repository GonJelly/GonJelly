package baekjoon;

import java.util.*;
import java.io.*;

public class Main_2504 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] text = in.readLine().split("");
		// 열린 괄호를 저장할 스택
		Stack<String> open = new Stack();
		Stack<String> cal = new Stack();
		// 임시2 괄호저장 스택
		Stack<String> open2 = new Stack();
		for(int i = text.length; i > 0; i--) {
			open2.push(text[i - 1]);
		}
		// 정상적인 괄호인지 식별할 변수
		boolean check = true;
		// start
		for (int i = 0, size = text.length; i < size; i++) {
			String t = text[i];
			// 열린 괄호라면 스택에 저장
			if (t.equals("(") || t.equals("[")) {
				// System.out.println("괄호 삽입 " + t);
				open.push(t);
			} else { // 닫힌 괄호라면 스택에서 제일 위에 있는 괄호랑 비교
				// 닫힌 괄호랑 쌍을 이루지 못한다면 올바른 괄호가 아님
				String pop = open.isEmpty() ? "" : open.pop();
				// System.out.println("괄호 꺼냄 : " + pop);
				// 스택에서 꺼낸 괄호 유효성 검사
				if( pop.equals("") || !bracketCompare(t, pop) ) {
					System.out.println("올바르지 않은 괄호입니다.");
					check = false;
					break;
				}
				// 연산 시작
				calculation(cal, t);
				String next = i + 1 < size ? text[i + 1] : "";
				// 이번기호가 닫힘기호이고 다음으로 꺼낼 괄호가 열림 괄호라면 + 기호를
				if( (t.equals(")") || t.equals("]")) &&
						(next.equals("(") || next.equals("[")) ) {
					cal.push("+");
				}
			}
		}
		if(cal.size() >= 3) {
			int first = Integer.parseInt(cal.pop());
			String expression = cal.pop(); // 어차피 + 이다.
			int second = Integer.parseInt(cal.pop());
			// System.out.printf("%d %s %d 계산을 하였습니다.\n",first, expression, second);
			int plus = first + second;
			cal.push(String.valueOf(plus));
		}
		System.out.println(check ? cal.pop() : 0);
	}

	private static boolean bracketCompare(String t, String compare) {
		switch(t) {
			case ")":
				return compare.equals("(");
			case "]":
				return compare.equals("[");
			default:
				return false;
		}
	}

	private static int bracketValue(String t) {
		switch(t) {
			case ")":
				return 2;
			case "]":
				return 3;
			default:
				return 0;
		}
	}

	private static void calculation(Stack<String> cal, String t) {
		// System.out.println("연산 시작 " + cal);
		String syntax = cal.isEmpty() ? "" : cal.pop();
		// 연산식을 저장할 스택이 비어 있으면 괄호값을 넣고, +부호라면 괄호값을 넣는다.
		if( syntax.equals("") ) {
			cal.push(String.valueOf(bracketValue(t)));
		} else if( syntax.equals("+") ) {
			cal.push(syntax);
			cal.push(String.valueOf(bracketValue(t)));
		} else { // 숫자라면 연산식을 수행한다.
			int p = bracketValue(t) * Integer.parseInt(syntax);
			cal.push(String.valueOf(p));
		}
	}
	/*
	private static int calculation2(Stack<String> text, int deep, String t) {

		if( text.isEmpty() ) {
			return 1;
		}

		int sum = 0;

		String next = text.pop();
		if( next.equals("(") || next.equals("[") ) {
			return bracketValue(t) * calculation2(text, deep + 1, next);
		} else if() {
			bracketValue(t);
		} else {
			return -1
		}
	}
	*/
}

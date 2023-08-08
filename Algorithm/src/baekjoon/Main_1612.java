package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1612 {

	private static long n;

	public static void main(String[] args) throws IOException {

		// 입력값을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 받은 정수를 숫자로 형변환
		n = Long.parseLong(br.readLine());
		// 1 로만 이루어진 양의 정수
		long s = 1;

		if( n % 2 == 0 || n % 5 == 0 ) {
			System.out.println(-1);
			return;
		} else if( n == 1 ) {
			System.out.println(1);
			return;
		}
		// 주어진 숫자의 자릿 수 만큼 s를 키워준다.
		int start = 1;
		for(int i = 2; s % n != 0; i++) {
			s = (s * 10 + 1) % n;
			if( s % n == 0) {
				System.out.println(i);
				break;
			}
			// System.out.println(s);
		}

	}

}

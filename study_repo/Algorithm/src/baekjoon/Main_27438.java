package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_27438 {

	private static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// double start = System.nanoTime();
		int number = 666;
		int cnt = 1;
		while(cnt != n) {
			number++;
			if(String.valueOf(number).contains("666")) {
				cnt++;
				// System.out.println(cnt + " : " + number);
			}
		}
		// double end = System.nanoTime();
		System.out.println(number);
	}
}

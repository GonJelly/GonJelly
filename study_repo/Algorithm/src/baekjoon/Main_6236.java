package baekjoon;

import java.util.*;
import java.io.*;

public class Main_6236 {

	private static int n,m;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// A가 지낼 날짜 | 인출 할 횟수
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// n일동안 하루 소비할 금액을 저장할 배열
		int[] poket = new int[n];
		// 제일 사용을 많이하는 날의 금액
		int maxUse = 0;
		int sum = 0;
		// 사용할 금액을 저장
		for(int i = 0; i < n; i++) {
			int money = Integer.parseInt(in.readLine());
			poket[i] = money;
			maxUse = Math.max(maxUse, money);
			sum += money;
		}

		int result = binarySearch(maxUse, sum, poket);
		System.out.println(result);
	}

	public static int binarySearch(int low, int high, int[] poket) {
		// 기저 조건
		if( low > high ) {
			return low;
		}

		// 중간 값을 구한다.
		int k = (low + high) / 2;
		int money = 0;
		int cnt = 0;
		// 해당 금액으로 n일 동안 지낼 수 있는지 검증
		for(int i = 0; i < n; i++) {
			// i번째날 보낼 금액
			int current = poket[i];
			// 만약 금액이 부족하면 mid 만큼 다시 인출
			if (current > money) {
				money = k;	// 금액인출
				cnt++;		// 인출 횟수 증가
			}
			money -= current;
		}

		if( cnt > m ) {
			return binarySearch(k + 1, high, poket);
		} else {
			return binarySearch( low , k - 1, poket);
		}
	}
}

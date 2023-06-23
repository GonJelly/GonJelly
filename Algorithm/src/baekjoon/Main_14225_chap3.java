package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.stream.Collectors;

public class Main_14225_chap3 {

	static int n, count;
	static List<Integer> store;
	static boolean[] check;

	public static void main(String args[]) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 초기화
		count = 0;
		store = new ArrayList<>();
		check = new boolean[200000001];
		n = Integer.parseInt(reader.readLine());
		StringTokenizer st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++ ) {
			store.add(Integer.parseInt(st.nextToken()));
		}
		int min = 1;
		// 시간 복잡도 계산 ( 시작 )
		Long start = System.nanoTime();
		// 수열로 만들 수 있는 모든 숫자를 구한다.
		dfs(0, 0);
		while( check[min] ) {
			min++;
		}
		System.out.println(min);
		// 시간복잡도 계산하기 ( 종료 )
		long end = System.nanoTime();
		System.out.println(((end - start) + "초"));
		System.out.printf("연산 횟수 : %d", count);

	}

	private static void dfs(int cnt, int num) {

		count++;
		if( cnt >= n ) {
			return;
		}

		check[num + store.get(cnt)] = true;
		dfs( cnt + 1, num );
		dfs( cnt + 1, num + store.get(cnt) );
	}
}

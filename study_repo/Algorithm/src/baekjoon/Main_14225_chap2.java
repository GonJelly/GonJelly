package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main_14225_chap2 {

	static int n, count;
	static List<Integer> store;

	public static void main(String args[]) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 초기화
		count = 0;
		n = Integer.parseInt(reader.readLine());
		int min = 1;
		// 입력값을 정수 리스트에 저장한다.
		store = Arrays.stream(reader.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		// 시간 복잡도 계산 ( 시작 )
		Long start = System.nanoTime();
		// 수열로 만들 수 있는 모든 숫자를 구한다.
		dfs(0, 0, 0);
		// 저장된 숫자들을 오름차순으로 정렬한다.
		for(int num : store.stream()
						.distinct()
						.sorted()
						.collect(Collectors.toList())
		) {
			if( num == min) {
				min++;
			} else {
				break;
			}
		}
		System.out.println(min);
		// 시간복잡도 계산하기 ( 종료 )
		long end = System.nanoTime();
		System.out.println(((end - start)) + "초");
		System.out.printf("연산 횟수 : %d", count);

	}

	private static void dfs(int cnt, int flag, int num) {
		if( cnt >= n ) {
			return;
		}

		for(int start = cnt; start < n; start++) {
			count++;
			if( (flag & ( 1 << start )) > 0 ) continue;
			int result = num + store.get(start);
			if( !store.contains(result) ) store.add(result);
			dfs( start + 1, flag | ( 1 << start), result );
		}
	}
}

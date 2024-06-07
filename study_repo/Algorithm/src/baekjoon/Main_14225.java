package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main_14225 {

	static int n;
	static List<Integer> store, valiable;

	public static void main(String args[]) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 초기화
		n = Integer.parseInt(reader.readLine());
		// 제일 작은 자연수
		int min = 1;
		// 만들 수 있는 숫자를 저장할 리스트
		valiable = new ArrayList<>();
		// 입력값을 정수 리스트에 저장한다.
		store = Arrays.stream(reader.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		// 시간 복잡도 계산 ( 시작 )
		Long start = System.nanoTime();
		// 저장된 숫자들을 오름차순으로 정렬한다.
		store.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		// 수열로 만들 수 있는 모든 숫자를 구한다.
		dfs(0, 0, 0);
		List<Integer> result = valiable.stream()
			.distinct()
			.sorted()
			.collect(Collectors.toList());

		// 연속되지 않는다면 해당 숫자는 만들 수 있는 걸로 보고 출력한다.
		for(int num : result) {
			if( min == num ) {
				min++;
			} else {
				break;
			}
		}
		System.out.println(min);
		// 시간복잡도 계산하기 ( 종료 )
		long end = System.nanoTime();
		System.out.println(((end - start) / 1000000) + "초");

	}

	private static void dfs(int cnt,int flag, int num) {
		if( cnt == n) {
			return;
		}

		for(int r = cnt; r < n; r++) {
			if( (flag & (1 << r)) > 0) continue;
			int result = num + store.get(r);
			valiable.add(result);
			dfs(cnt + 1, flag | (1 << r),result);
		}
	}
}

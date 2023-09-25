package baekjoon;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main_1654 {

	private static int k, n;

	public static void main(String[] args) throws IOException {
		long time_s = System.nanoTime();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫번째 줄 보유하고 있는 랜선 k, 만들어야하는 랜선 n
		List<Integer> input = Arrays.stream(br.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		k = input.get(0);
		n = input.get(1);
		// k + 1 번째 줄까지 각 k랜선들의 길이
		List<Integer> list_k = new ArrayList();
		int k_1 = 0;		// k 랜선들에서 가장 길이가 긴 랜선
		for(int i = 0; i < k;i ++) {	// 10,000번
			int temp = Integer.parseInt(br.readLine());
			list_k.add(temp);
			k_1 = Math.max(k_1,temp);
		}

		// 출력해야하는 값 : 랜선 n의 최대 길이
		int start = 0;			// 시작
		int end = k_1;	// 제일 긴 랜선
		int max = 0;			// 최대 길이
		while( start < end ) {
			int m = (start + end) / 2;// 만들어야하는 랜선의 길이
			int r = 0;				// m의 길이의 랜선의 갯수
			// k개의 랜선들을 m의 길이로 절단
			for(int k_2 : list_k) {	// 10,000번
				r += k_2 / m;
			}
			System.out.printf("start : %d end : %d m : %d\n",start, end, m);
			// r < n 이면 end를 m의 길이로
			if( r < n ) {
				end = m - 1;
				System.out.println("m의 길이가 길다. (" + m + "cm)");
			}
			// r > n 이면 start의 깅이를 m으로
			else if( r >= n ) {
				start = m + 1;
				max = m;
				System.out.println("m의 길이가 짧다. (" + m + "cm)");
			}
		}
		System.out.println(max);
		long time_e = System.nanoTime();
		System.out.println(time_e - time_s / 1_000_000_000 + "초");
	}
}

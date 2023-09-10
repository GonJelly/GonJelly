import java.io.*;
import java.util.*;

public class Main {

	private static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 주전자의 수 | 은상을 포함한 친구들의 수 ( 사람들 수 ) | 제일 많이 들어있는 주전자의 량
		n = Integer.parseInt(st.nextToken()); // 1 <= n <= 10_000
		k = Integer.parseInt(st.nextToken()); // 1 <= k <= 1_000_000, 단 n <= k
		long alc = 0;
		// n개의 주전자의 막걸리 량을 저장할 배열
		int[] makguly = new int[n];
		for(int i = 0; i < n; i++) {
			int ml = Integer.parseInt(br.readLine()); // 0 <= ml <= 2^31 - 1
			makguly[i] = ml;
			alc = Math.max(alc, ml);
		}
		// 나눠줄 수 있는 막걸리의 최대량을 구한다.
		long cnt = binarySearch(1, alc, makguly);
		// 결과를 춮력한다.
		System.out.println(cnt);
	}

	public static long binarySearch(long low, long high, int[] makguly) {

		if(low > high) {
			return high;
		}
		// 나눠줄 막걸리 량을 설정
		long mid = (low + high) / 2;
		// 설정된 양만큼 막걸리를 나눠준다.
		// 나눠준 사람 수
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			int ml = makguly[i];
			cnt += (ml / mid);
		}

		if( cnt >= k) { // 나눠준 사람이 k보다 적으면 막걸리 양이 적다는 뜻, 막걸리 량을 늘린다.
			return binarySearch(mid + 1, high, makguly);
		} else { // 나눠준 사람이 k보다 적으면 막걸리 양이 많다는 뜻, 막걸리 량을 줄인다..
			return binarySearch(low, mid - 1, makguly);
		}

	}

}
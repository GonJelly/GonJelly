package baekjoon;

import java.util.*;
import java.io.*;

public class Main_2110 {

	private static long n, c, count, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());	// 집의 갯수 2 <= n <= 200_000
		c = Integer.parseInt(st.nextToken());	// 공유기의 갯수	2 <= c <= n

		List<Integer> homes = new ArrayList();		// 집의 좌표들
		long low = 0;
		long high = 0;

		for(int i = 0; i < n; i++) {
			int coordinate = Integer.parseInt(br.readLine());
			low = Math.min(coordinate, low);	// 제일 낮은 위치에 있는 집
			high = Math.max(coordinate, high);	// 제일 높은 위치에 있는 집
			homes.add(coordinate);
		}

		Collections.sort(homes);				// 리스트 정렬

		count = 2;							// 처음 / 끝 공유기 설치
		max = high - low;					// 공유기의
	}

	private static void binarySearch(long low, long high, List<Integer> homes) {
		if( low >= high || count == c) {
			return;
		}
		// 중간값을 확인
		long mid = low + (high - low) / 2;
		if(homes.contains(mid)) {
			count++;
		}

		binarySearch(low, mid, homes);
		binarySearch(mid, high, homes);

	}
}

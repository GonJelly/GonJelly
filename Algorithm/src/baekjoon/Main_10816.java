package baekjoon;

import java.util.*;
import java.io.*;

public class Main_10816 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		int n = Integer.parseInt(br.readLine());		// 1 <= n <= 500_000
		int[] card1 = new int[n];						// 카드 숫자 -10_000_000 <= k <= 10_000_000

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			card1[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(card1);

		int m = Integer.parseInt(br.readLine());
		int[] targets = new int[m];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(Arrays.toString(card1));
		for(int target : targets) {
			int index = lower_bound(card1, target);
			int highter = lower_bound(card1, target + 1);
			output.append(highter - index + " ");
		}
		System.out.println(output);
	}

	private static int lower_bound(int[] card, int x) {
		int start = 0;
		int end = card.length;
		int result = -1;
		while(start < end) {
			int mid = start + (end - start) / 2;
			if(card[mid] >= x) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}

package baekjoon.label1000;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 터렛
*  완전 탐색 불가
*  O(n^2) => 10,000 * 10,000 =>
* */
public class Main_1002 {

	public static void main(String[] args) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(System.in);
		BufferedReader read = new BufferedReader(new InputStreamReader(bis));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int t = Integer.parseInt(read.readLine());
		// 테스트 start
		for ( int i = 0; i < t; i++ ) {

			StringTokenizer st = new StringTokenizer(read.readLine());

			long x1 = Integer.parseInt(st.nextToken());
			long y1 = Integer.parseInt(st.nextToken());
			long r1 = Integer.parseInt(st.nextToken());
			long x2 = Integer.parseInt(st.nextToken());
			long y2 = Integer.parseInt(st.nextToken());
			long r2 = Integer.parseInt(st.nextToken());

			int result = (int)getdistance(x1,y1,x2,y2);
			double distance1 = Math.pow(r2 + r1,2);
			double distance2 = Math.pow(r2 - r1,2);

			if(x1 == x2 && y1 == y2 && r1 == r2 ) {
				sb.append(-1);
			} else if(result > distance1 || result < distance2) {
				sb.append(0);
			} else if(result == distance1 || result == distance2) {
				sb.append(1);
			} else {
				sb.append(2);
			}

			if( i != t - 1) {
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

	private static double getdistance(long x1, long y1, long x2, long y2) {
		double horizon = Math.pow(x2-x1,2);
		double vertical = Math.pow(y2-y1,2);
		return horizon + vertical;
	}
}

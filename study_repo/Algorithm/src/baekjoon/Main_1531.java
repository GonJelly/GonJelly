package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1531 {

	static int n, l, count;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());	// 종이의 갯수
		l = Integer.parseInt(st.nextToken());	// 그림이 보이는 기준 ( 최대 종이 갯수 )
		count = 0;
		int[][] board = new int[101][101];			// 그림판 초기화

		// 입력받은 종이의 모서리 좌표를 이용해서 그림판에 올라가는 종이의 갯수를 카운트 ( 0 < n <= 50 )
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			// 카운트 시작
			for(int r = x1; r <= x2; r++) {
				for( int c = y1; c <= y2; c++) {
					board[r][c]++;
				}
			}
		}

		for(int r = 1; r <= 100; r++) {
			for(int c = 1; c <= 100; c++) {
				if( board[r][c] > l ) {
					count++;
				};
			}
		}

		System.out.println(count);
	}

}

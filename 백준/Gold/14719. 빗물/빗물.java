import java.util.*;
import java.io.*;

public class Main {

	private static int h,w;
	private static boolean[][] board;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 벽의 세로 | 가로
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		// 물이 고일 수 있는 공간
		board = new boolean[w][h];
		// 입력값을 받는다.
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < w; i++) {
			int height = Integer.parseInt(st.nextToken());
			for(int j = 0; j < height; j++) {
				board[i][j] = true;
			}
		}
		// 고인 물의 량
		int cnt = 0;
		for(int r = 0; r < h; r++) {
			int start = preOrder(r);
			int end = postOrder(r);
            // 빗물이 고인다.
			if( start < end ) {
				for(int i = start; i < end; i++) {
					cnt = board[i][r] ? cnt  : cnt  + 1;
				}
			}
		}
        // 고인 물의 량 출력
		System.out.println(cnt);
	}

	public static int preOrder(int h) {
		for(int i = 0; i < w; i++) {
			if( board[i][h]) {
				return i;
			}
		}
		return -1;
	}

	public static int postOrder(int h) {
		for(int i = w - 1 ; i >= 0; i--) {
			if( board[i][h] ) {
				return i;
			}
		}
		return -1;
	}
}
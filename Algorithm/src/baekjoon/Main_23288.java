package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288 {

	static int[][] direct = {
		{0,1},            // 동쪽
		{-1,0},            // 북쪽
		{0,-1},            // 서쪽
		{1,0}            // 남쪽
	};
	static int n, m, A, B, C;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력값 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int total = 0;				// 최종 정답 값 ( 합산된 값 )
		// 초기 좌표
		int[] current = {1,1};
		// 초기 방향
		int way = 0;
		// 주사위 초기화
		int[][] dice = new int[][] {
			{-1,2,-1},
			{4,1,3},
			{-1,5,-1}
		};
		// 입력값 초기화
		int[][] board = new int[n + 1][m + 1];
		for(int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= m; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
			System.out.println(Arrays.toString(board[r]));
		}

		A = 6;            // 주사위의 맨밑 숫자
		B = board[1][1];// 주사위가 있는 좌표의 값
		C = 0;            // 사방탐색으로 이동할 수 있는 숫자들의 합
		// 주사위를 움직입니다.
		for(int i = 0; i < k; i++) {
			// 주사위가 움직인 보드의 숫자
			int x = current[0] + direct[way][0];        // x좌표
			int y = current[1] + direct[way][1];        // y좌표
			//     범위를 검사한다. 움직인 곳이 보드를 벗어난다면 반대 방향으로 이동한다.
			if( isCheck(x, y) ) {
				way = (way + 2) % 4;
				x = current[0] + direct[way][0];
				y = current[1] + direct[way][1];
			}
			// 주사위 도면도를 움직인다. AND A의 값을 주입합니다.
			move(dice, way);
			System.out.println("이동하였습니다.");
			System.out.printf("x : %d y : %d \n",x,y);
			// 이동한 곳의 숫자를 주입한다.
			current = new int[] {x,y};
			B = board[current[0]][current[1]];
			// 방향전환
			if(A > B) {
				way -= 1;
				if( way < 0) way = 3;
			} else if(A < B) {
				way += 1;
				if( way > 3) way = 0;
			}
			System.out.printf("A : %d B : %d \n",A,B);
			// 보드의 숫자와 동일한 숫자를 bfs로 탐색해서 총합을 반환한다.
			C = bfs(board, current);
			System.out.printf("B : %d C : %d \n",B,C);
			total += B * C;
			System.out.println(total);
		}
	}

	/**
	 *  범위를 체크하는 함수
	 * */
	private static boolean isCheck(int x, int y) {
		if( x > 0 && x <= n && y > 0 && y <= m ) {
			return false;
		}
		return true;
	}

	private static void move(int[][] dice, int way) {
		int temp = 0;
		switch(way) {
			case 0:    // 동쪽으로 이동할 경우
				System.out.println("동쪽으로");
				temp = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = dice[1][0];
				dice[1][0] = A;
				A = temp;
				printDice(dice);
				break;
			case 1: // 위로 이동할 경우
				System.out.println("위로");
				temp = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = A;
				A = temp;
				printDice(dice);
				break;
			case 2: // 서쪽으로 이동할 경우
				System.out.println("서쪽으로");
				temp = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = A;
				A = temp;
				printDice(dice);
				break;
			case 3: // 아래로 이동할 경우
				System.out.println("아래로");
				temp = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = dice[0][1];
				dice[0][1] = A;
				A = temp;
				printDice(dice);
				break;
			default:
				break;
		}
	}

	private static int bfs(int[][] board, int[] current) {
		// 무한루프를 방지하고자 체크배열 생성
		boolean[][] valiable = new boolean[n + 1][m + 1];

		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(current);
		int num = board[current[0]][current[1]];
		valiable[current[0]][current[1]] = true;
		int count = 1;
		while( !que.isEmpty() ) {
			int[] temp = que.poll();    // 숫자의 좌표
			// 4방향 탐색
			for(int i = 0; i < 4; i++) {
				int x = temp[0] + direct[i][0];
				int y = temp[1] + direct[i][1];
				// 보드의 범위 안에 있고 현재 위치에 숫자와 동일한 숫자들
				if( !isCheck(x, y) && board[x][y] == num ) {
					if( valiable[x][y] ) continue;
					count++;
					que.offer(new int[] {x, y});
					valiable[x][y] = true;
				}
			}
		}
		return count;
	}

	private static void printDice(int[][] dice) {
		for(int i = 0; i < 3; i++) {
			System.out.println(Arrays.toString(dice[i]));
		}
	}
}

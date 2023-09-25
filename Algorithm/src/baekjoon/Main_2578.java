package baekjoon;

import java.util.*;
import java.io.*;

// 1초, 128MB
public class Main_2578 {

	private static final int VERTICAL = 5, HORIZON = 5;
	private static final int FIVE = 5;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 철수의 빙고숫자 저장
		int[][] user = talkNum(br);
		// 사회자가 언급한 빙고숫자들 저장
		int[][] mc = talkNum(br);
		int result = game(user, mc);
		System.out.println(result);
	}

	public static int[][] talkNum(BufferedReader input) throws IOException {
		// System.out.println("빙고판 제작");
		// 빙고판
		int[][] store = new int[VERTICAL][HORIZON];
		// 번호저장
		for(int x = 0; x < VERTICAL; x++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			for(int y = 0; y < HORIZON; y++) {
				int number = Integer.parseInt(st.nextToken());
				store[x][y] = number;
			}
		}
		// 생성한 빙고판 리턴
		return store;
	}

	public static int game(int[][] user, int[][] mc) {
		for(int i = 0; i < VERTICAL; i++) {
			for(int j = 0; j < HORIZON; j++) {
				// 사회자가 언급한 숫자 선택 = 0으로 변경
				pick(user, mc[i][j]);
				// 숫자가 빙고인지 점검 | 단) 3빙고는 최소 12번이상 호출해야됨
				if( i > 1) {
					int total = 0;
					total = bingo(user);
					if(total >= 3) {
						return i * 5 + (j + 1);
					}
				}
			}
		}
		return -1;
	}

	public static void pick(int[][] user, int num) {
		for(int i = 0; i < VERTICAL; i++) {
			for(int j = 0; j < HORIZON; j++) {
				if(user[i][j] == num) {
					user[i][j] = 0;
					return;
				}
			}
		}
	}

	public static int bingo(int[][] user) {
		// 빙고횟수
		int bingo = 0;
		// 검사 시작 ( 가로, 세로 )
		for(int r = 0; r < VERTICAL; r++) {
			int ver = 0;
			int hor = 0;
			for(int c = 0; c < HORIZON; c++) {
				ver = user[r][c] == 0 ? ver + 1 : ver;
				hor = user[c][r] == 0 ? hor + 1 : hor;
			}
			bingo = ver == 5 ? bingo + 1 : bingo;
			bingo = hor == 5 ? bingo + 1 : bingo;
		}

		// 검사 시작 (대각선)
		int lv = 0;
		int rv = 0;
		for(int i = 1; i <= VERTICAL; i++) {
			lv = user[i - 1][i - 1] == 0 ? lv + 1 : lv;
			rv = user[VERTICAL - i][i - 1] == 0 ? rv + 1 : rv;
		}
		bingo = lv == 5 ? bingo + 1 : bingo;
		bingo = rv == 5 ? bingo + 1 : bingo;

		return bingo;
	}
}

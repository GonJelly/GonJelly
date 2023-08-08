package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_28126_chap1 {

	// 1. 로봇의 이동횟수
	static int n, k, count;
	static String move;
	static int[] isCheck;
	public static void main(String []args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = 0;
		// 이동횟수
		n = Integer.parseInt(br.readLine());
		isCheck = new int[3];
		// 이동방향 순서 ( 첫번째는 0부터 )
		move = br.readLine();
		// R,U,X의 갯수를 카운팅한다. [0] => R, [1] => U, [2] => X
		for(char ch : move.toCharArray()) {
			switch(ch) {
				case 'R':
					isCheck[0]++;
					break;
				case 'U':
					isCheck[1]++;
					break;
				case 'X':
					isCheck[2]++;
					break;
			}
		}
		// 탐색을 하고싶은 지점의 갯수
		k = Integer.parseInt(br.readLine());
		// 입력받은 좌표를 탐사가 가능한지 확인한다.
		for(int i = 0; i < k; i++) {
			String[] dir = br.readLine().split(" ");
			int x = Integer.parseInt(dir[0]);
			int y = Integer.parseInt(dir[1]);
			onSearch(x - 1, y - 1);
		}
		System.out.println(count);
	}

	public static void onSearch(int x, int y) {
		int d = Math.min(isCheck[2],Math.min(x, y));
		x -= d;
		y -= d;
		if(isCheck[0] >= x && isCheck[1] >= y) count++;
	}

}

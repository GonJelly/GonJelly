package baekjoon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23280 {

	static int N, a, b, result;
	static int[] left, right;

	public static void main(String arg[]) throws IOException {

		// 게임판을 초기화한다.
		int[][] game = {
			{0,0}, // 1
			{0,1}, // 2
			{0,2}, // 3
			{1,0}, // 4
			{1,1}, // 5
			{1,2}, // 6
			{2,0}, // 7
			{2,1}, // 8
			{2,2}, // 9
			{3,0}, // 10
			{3,1}, // 11
			{3,2}, // 12
		};

		//입력값을 받아온다.
		BufferedInputStream bis = new BufferedInputStream(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		StringTokenizer st = new StringTokenizer(reader.readLine());

		// 입력 순선 : 정수의 갯수, A의 체력, B의 체력
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		result = 0;
		// 왼쪽 엄지의 좌표, 오른쪽 엄지의 좌표를 저장한다.
		left = new int[]{0,0};
		right = new int[]{0,2};

		// 정수들을 담아둘 자료구조 ( 배열 )
		int[] seq = new int[N];
		// 정수를 저장한다.
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < N; i++ ) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, seq, game);
	}

	// 엄지와 눌러야하는 숫자와의 거리를 계산
	private static int distance(int[] thumb, int[] target) {
		return Math.abs(thumb[0] - target[0]) + Math.abs(thumb[1] - target[1]);
	}

	// n번만큼 반복한다.
	private static void dfs(int cnt, int[] seq, int[][] game) {
		// 기저조건
		if( cnt == N ) {
			System.out.print(result);
			return;
		}

		//해당숫자의 좌표값을 불러온다.
		int num = seq[cnt] - 1;
		// 게입판에서 해당 숫자의 좌표값을 불러온다.
		int[] target = game[num];
		// 해당 좌표값과 각각의 엄지와의 거리를 계산한다.
		// 왼손엄지
		int disA = distance(left, target) + a;
		// 오른손 엄지
		int disB = distance(right, target) + b;

		// 적은 비용이 들어가는 엄지를 비교
		if(disA > disB) {
			right = target;
			result += disB;
		} else if(disA < disB) {
			left = target;
			result += disA;
		} else {
			if( a > b ) {
				// left = target;
				result += disA;
			} else {
				// right = target;
				result += disB;
			}
		}
		// 해당 좌표로 이동 시키고 다음으로 넘어간다.
		dfs(cnt + 1, seq, game);
	}
}

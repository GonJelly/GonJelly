package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_28126 {

	// 1. 로봇의 이동횟수
	static int n, k, count;
	static List<int[]> search;
	static boolean[] isVisit;
	static String move;
	public static void main(String []args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = 0;
		// 입력값을 입력받는다.
		// 이동횟수
		n = Integer.parseInt(br.readLine());
		// 이동방향 순서 ( 첫번째는 0부터 )
		move = br.readLine();
		// 탐색을 하고싶은 지점의 갯수
		k = Integer.parseInt(br.readLine());
		isVisit = new boolean[k];
		search = new ArrayList<int[]>();
		// 탐색하고 싶은 각 지정의 좌표를 저장할 배열
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			search.add(new int[]{x,y});
		}
		// 임의로 이동방향을 선택 시작 ( 시작좌표가 1,1 부터 시작하기 때문에 다움과 같이 하였습니다.
		recursion(0, 1, 1);
		System.out.println(count);
	}

	private static void recursion(int cnt, int x, int y) {
		// 기저조건 끝까지 왔을 때 ( 이동횟수 만큼 왔을 때 종료 )
		if( cnt == k ) {
			for(int i = 0; i < k; i++) {
				int[] dir = search.get(i);
				// X좌표값과 Y좌표값이 같고 한번도 카운팅되지 않는 좌표여야한다.
				if( dir[0] == x && dir[1] == y && !isVisit[i]) {
					isVisit[i] = true;
					count++;
				}
			}
			return;
		}
		// 이동방향의 값을 저장할 변수
		int _x = 0;
		int _y = 0;
		// 해당 움직임에 맞는 값을 기입
		switch(move.charAt(cnt)) {
			case 'R':
				_x += 1;
				break;
			case 'U':
				_y += 1;
				break;
			case 'X':
				_x += 1;
				_y += 1;
				break;
		}
		// 임의의 이동방향으로 이동을 선택할 경우
		recursion(cnt + 1, x + _x, y + _y);
		// 선택하지 않는 경우
		recursion(cnt + 1, x, y);
	}
}

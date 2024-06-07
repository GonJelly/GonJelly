package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main_21606 {

	static int N;
	static int[][] trunk;

	public static void main(String[] args) throws IOException {

		// 입력값 받기
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 첫번째 줄 정점의 수
		N = Integer.parseInt(reader.readLine());
		// 두번째 줄 실내/실외 판별 [0]실외 [1]실내
		List<Integer> check = Arrays.stream(reader.readLine().split(""))
									.map(Integer::parseInt)
									.collect(Collectors.toList());

		// 산책 경로의 수 초기화
		int count = 0;
		// 정점간의 간선 유무를 2차원배열로 초기화
		trunk = new int[N][N];

		// 세번째 줄 정점 과 정점을 잇는 간선 ( 20만번 )
		for(int i = 0; i < N - 1; i++) {
			int confirm = 0;
			String[] nodes = reader.readLine().split(" ");
			// 첫번째 정점과 두번째 정점을 구분한다.
			int first = Integer.parseInt(nodes[0]) - 1;
			int second = Integer.parseInt(nodes[1]) - 1;

			confirm = check.get(first) + check.get(second);

			if( confirm == 2 ) { // [실내 - 실내]일 경우, 산책경로 +2
				count += 2;
			} else { // [실내 - 실외], [실외 - 실내], [실외 - 실외]
				trunk[first][second] = trunk[second][first] = 1;
			}
		}

		for(int start = 0; start < N; start++) {
			if( check.get(start) == 1) {
				count += dfs(start, check);
			}
			// System.out.println(count);
		}
		// 결과 출력
		System.out.println(count);
	}

	private static int dfs(int start, List<Integer> check) {

		// 순환을 막기 위한 반문여부 판별
		boolean[] visit = new boolean[N];
		int cnt = 0;
		Stack<Integer> st = new Stack();
		// 방문 check
		visit[start] = true;
		st.push(start);
		while(!st.isEmpty()) {
			// System.out.print("-> ");
			int temp = st.pop();
			for(int i = 0; i < N; i++) {
				int v = trunk[temp][i];
				// 간선이 연결되었다면 stack에 삽입한다.
				if( v == 1 && !visit[i] ) {
					// 방문 check
					visit[i] = true;
					// 끝점이 실내이면 카운트
					if( check.get(i) == 1 ) {
						cnt++;
					} else {
						st.push(i);
					}
				}
			}
		}
		// 산택 경우의 수 반환
		return cnt;
	}
}

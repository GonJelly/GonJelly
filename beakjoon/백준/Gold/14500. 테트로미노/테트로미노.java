import java.util.*;
import java.io.*;

public class Main {

	private static int N, M, max;
	private static int[][] direct = new int[][] {
		{-1,0},{0,1},{1,0},{0,-1}
	};
	private static int[][] board;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 보드의 크기를 저장할 변수 N, M 할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 보드에 각 칸에 숫자를 저장할 이차원 변수
		board = new int[N][M];
		// 순환을 막기위해서 방문여부 체그 배열
		visited = new boolean[N][M];
		// 보드에 숫자를 할당
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
			// System.out.println(Arrays.toString(board[r]));
		}
		// 테트로미노가 포함한 숫자들의 합에 대한 최댓값을 구하기
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < M; y++) {
				// 시작점은 방문처리
				visited[x][y] = true;
				// 시작 좌표 [x,y] 최댓값 구하기 시작
				tetromino(0, new int[] {x, y}, board[x][y]);
				// 방문여부 해제
				visited[x][y] = false;
				bfs(new int[] {x,y}, board[x][y]);
			}
		}
		// 최댓값 출력
		System.out.println(max);
	}

	/**
	 * @apiNote : 데트로미노가 놓였을 때 최댓값을 구하는 method
	 * */
	private static void tetromino(int cnt, int[] start, int sum) {
		// 기저조건 : 깊이가 4가 되었을 때
		if( cnt == 3 ) {
			max = Math.max(max, sum);
			return;
		}
		// 4방향 탐색
		for(int i = 0; i < 4; i++) {
			// 시작점에서 다음칸 좌표를 구한다.
			int nx = start[0] + direct[i][0];
			int ny = start[1] + direct[i][1];
			// 보드의 크기를 벗어나지 않았는지 체크 || 해당 좌표를 방문한 적인 있으면 다음 좌표를 구한다.
			if(isCheck(nx, ny) || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			// 해당 좌표의 값을 플러스하고 next
			tetromino(cnt + 1,new int[] {nx, ny}, sum + board[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	private static void bfs(int[] start, int sum) {
		for(int x = 0; x < 4; x++) {
			int plus = sum;
			// 3방향을 플러스 했는지 검사하는 변수
			int count = 1;
			// 시작점에서 다음칸 좌표를 구한다.
			int nx = start[0] + direct[x][0];
			int ny = start[1] + direct[x][1];
			if(isCheck(nx,ny)) continue;
			plus += board[nx][ny];
			while(count < 3) {
				int way = (x + count) % 4;
				int nnx = start[0] + direct[way][0];
				int nny = start[1] + direct[way][1];
				// 한번이라도 범위를 벗어났으면 종료
				if(isCheck(nnx,nny)) break;
				// 검사 증강
				count++;
				// 합산
				plus += board[nnx][nny];
			}
			// 3방향을 검사한게 아니라면 다음 검사
			if( count != 3 ) continue;
			max = Math.max(max, plus);
		}
	}

	private static boolean isCheck(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) {
			return true;
		}
		return false;
	}
}
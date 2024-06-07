package baekjoon;

import java.util.*;
import java.io.*;

public class Main_16234_temp  {

	private static int N, L, R;
	private static int[][] map, direction = {
		{-1,0},{0,1},{1,0},{0,-1}
	};
	private static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());	// 토지의 크기
		L = Integer.parseInt(st.nextToken());	// 인구 차이의 범위 최소
		R = Integer.parseInt(st.nextToken());	// 인구 차이의 범위 최대
		// 땅의 인구를 저장할 공간 (초기화)
		map = new int[N][N];
		// 땅의 오픈 여부를 판단할 불린 배열
		visit = new boolean[N][N];
		// 인구 할당
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int next;
		int day = 0;
		do {
			visit = new boolean[N][N];
			next = 0;
			// N x N의 크기의 땅을 탐색해서 연합을 조사한다.
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					// 방문하지 않았던 나라여야 한다.
					if (!visit[x][y]) {
						// 해당 좌표에서 넓비 우선 탐색으로 연합을 조사
						next += bfs(x, y);
					}
				}
			}
			if(next > 0) {
				day++;
			}
		} while(next > 0);
		System.out.println(day);

	}

	private static int bfs(int x, int y) {

		// 연합들의 좌표를 저장할 리스트
		List<int[]> list = new ArrayList<>();
		// 4방 탐색할 나라의 좌표를 저장할 큐
		Queue<int[]> que = new LinkedList();
		que.offer(new int[] {x, y});
		// 연합을 생성했는지 여부 = 국경을 오픈여부
		visit[x][y] = true;
		int us = 0;

		while( !que.isEmpty() ) {
			int[] coordinate = que.poll();
			int cx = coordinate[0];
			int cy = coordinate[1];
			// 리스트 저장
			list.add(coordinate);
			// 4방 탐색
			for(int i = 0; i < 4; i++) {
				int nx = cx + direction[i][0];
				int ny = cy + direction[i][1];
				// 토지의 범위를 벗어나지 않았는지 유효성 검사 => 인구 수의 차이가 L <= person <= R 사이에 존재하지는 검사( 절댓값 비교 )
				// 해당 국가를 방문한 적이 없어야한다.
				if(checkSum(nx, ny) && isDiff(Math.abs(map[cx][cy] - map[nx][ny])) && !visit[nx][ny]) {
					// 해당 좌표는 국경선을 오픈(방문)했다고 보아야한다. ( 즉 visit를 true로 변경 )
					visit[nx][ny] = true;	// 방문함
					us = 1; // 연합 형성이 됨
					// 다음 국가와 국경선을
					que.offer(new int[]{nx, ny});
				}
			}
		}
		// System.out.println("연합을 형성한 나라의 갯수 : " + list.size());
		// 연합의 인구 이동 시작
		int sum = 0;
		for(int[] pos : list) {
			sum += map[pos[0]][pos[1]];
		}
		int avg = sum / list.size();
		// 연합의 인구 이동 시작
		list.stream().forEach(pos -> {
			map[pos[0]][pos[1]] = avg;
		});

		return us;

	}

	private static boolean checkSum(int x, int y) {
		if( x < 0 || x >= N || y < 0 || y >= N ) {
			return false;
		}
		return true;
	}

	private static boolean isDiff(int person) {
		if( person >= L & person <= R) {
			return true;
		}
		return false;
	}
}
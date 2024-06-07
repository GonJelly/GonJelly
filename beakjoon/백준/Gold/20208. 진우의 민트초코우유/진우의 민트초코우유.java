import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

	private static int N, H, max;
	private static int[] HOME = new int[2];
	private static List<int[]> minchos = new ArrayList(); 	// 민초의 좌표를 저장할 List

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());		// 마을의 크기
		int M = Integer.parseInt(st.nextToken());	// 진우의 체력
		H = Integer.parseInt(st.nextToken());		// 체력 imcrement
		boolean[][] maps = new boolean[N][N];		// 이동여부를 판별할 지도

		int[] jinu = new int[2];					// 시작위치 = 집

		// 해당 좌표값을 알아야하기 때문에 전통적인 for문을 사용
		for(int i = 0; i < N; i++) {
			String[] direct = br.readLine().split(" ");
			// N만큼의 크기의 마을의 상태를 체크
			for(int j = 0, size = direct.length; j < size; j++) {
				int value = Integer.parseInt(direct[j]);
				if( value == 2 ) { // 만약 해당 값이 민초라면 민초에 저장
					minchos.add(new int[]{i, j});
					maps[i][j] = true;
					// System.out.println("x : " + i + " y : " + j);
				} else if( value == 1 ) { // 1 이라면 집이기 때문에 진우의 시작위치
					jinu[0] = HOME[0] = i;			// x좌표
					jinu[1] = HOME[1] = j;			// y좌표
					maps[i][j] = true;
					// System.out.println("민우의 시작 위치 : " + Arrays.toString(jinu));
				}
			}
		}

		// 진우의 위치 와 내가 먹은 민초의 갯수를 매개변수로 갖는 메소드
		// 기저 조건은 민우의 위치가 다시 집으로 돌아 왔을 경우
		// + 매개변수로 민초의 좌표로 이동이 가능한지 2차원 배열을 만들어야할 듯
		// 만약 이동할 수 있는 좌표가 없다면 종료 ( 백트래킹 )
		dfs(0, M, jinu, maps);
		// 결과 출력
		System.out.println(max);
	}

	/**
	 * 민초의 갯수를 찾는 메소드
	 * @author 박준영
	 * @param cnt 민초 갯수
	 * @param current 현재 체력
	 * @param coordinate 진우의 좌표
	 * @param maps 이동가능 판별 지도
	 *
	 */
	private static void dfs(int cnt, int current, int[] coordinate, boolean[][] maps) {

		// System.out.printf("민초갯수 : %d 현재체력 : %d 좌표 : [%d,%d]\n",cnt,current,coordinate[0],coordinate[1]);
		// 기저조건 : 집에 도착할 경우 ( HOME의 좌표가 false라면 다시 집으로 돌아온거로 간주한다. )
		// 최대 갯수를 비교해서 값을 변경할지 정한다.
		if(!maps[HOME[0]][HOME[1]] & coordinate[0] == HOME[0] && coordinate[1] == HOME[1]) {
			// System.out.println("집으로 돌아왔어요.");
			max = Math.max(max, cnt);
			return;
		}
		// 이동가능한 민초의 좌표를 저장할 리스트
		// 진우의 위치에서 상,하,좌,우 탐색
		List<int[]> search = searchMinch(coordinate, current, maps);
		// for(int[] mincho : search ) {
		// 	System.out.println("이동 가능 좌표 : " + Arrays.toString(mincho));
		// }
		// 만약 리스트의 size가 0라면 백트래킹(return)
		// 이동한 좌표는 방문하지 않는 걸로 처리를 해줘야함.
		if( search.isEmpty() ) {
			maps[coordinate[0]][coordinate[1]] = true;
			return;
		}
		// 리스트가 존재할 경우
		// 해당 좌표로 이동해서 완전탐색 진행
		for(int[] mincho : search ) {
			int x = mincho[0];	// x 좌표
			int y = mincho[1];	// y 좌표
			int distance = Math.abs(coordinate[0] - x) + Math.abs(coordinate[1] - y);
			// 해당 민초의 좌표로 변경
			// maps에서 해당 좌표는 false로 변경하고 넘겨야함
			// 체력을 거리만큼 마이너스 & 민초를 먹었으니 H만큼 플러스 해줘야함
			// cnt의 갯수 +1 해준다.
			if( (HOME[0] == x && HOME[1] == y) & distance <= current & maps[x][y]) {	// 집이라면 민초추가 없음, 체력회복 없음
				maps[x][y] = false;
				dfs(cnt, current - distance, new int[] {x, y}, maps );
			} else if(distance <= current & maps[x][y]){
				maps[x][y] = false;
				dfs(cnt + 1, current - distance + H, new int[] {x, y}, maps);
			}
			maps[x][y] = true;
		}

	}

	private static List<int[]> searchMinch(int[] coordinate, int current, boolean[][] maps) {

		List<int[]> search = new ArrayList();

		// 집으로 이동여부도 체크해줘야함
		int comeback = Math.abs(coordinate[0] - HOME[0]) + Math.abs(coordinate[1] - HOME[1]);
		if(comeback != 0 & comeback <= current && maps[HOME[0]][HOME[1]]) {
			search.add(HOME);
		}

		for(int[] mincho : minchos) {
			int x = mincho[0];	// x 좌표
			int y = mincho[1];	// y 좌표
			// 진우의 위치와 민초의 위치를 절댓값으로 계산해서 거리를 계산한다.
			int distance = Math.abs(coordinate[0] - x) + Math.abs(coordinate[1] - y);
			// 거리가 현재 체력 M 으로 이동 가능 & 해당 좌표로 이동이 유효하면 추가한다.
			if( distance <= current & maps[x][y] ) {
				search.add(mincho);
			}
		}

		return search;
	}
}
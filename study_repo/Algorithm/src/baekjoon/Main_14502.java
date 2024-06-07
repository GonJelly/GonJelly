package baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 단순무식 하드코딩
public class Main_14502 {
    // 상좌하우
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static int r,c,max, mk[][];
    static boolean[][] visit;   // 방문여부 체크
    static ArrayList<Point> virus; // 바이러스가 있는 좌표

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 연구소의 크기를 지정해준다.
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        // 안전구간의 최댓값
        max = 0;
        // 세울수 있는 벽의 갯수는 3개 무조건 3개를 세워야한다.
        mk = new int[3][2];
        visit = new boolean[r][c];
        virus = new ArrayList<>();

        int[][] lab = new int[r][c];
        // 0:빈칸 1:벽 2:바이러스
        for(int x = 0; x < r; x++) {
            st = new StringTokenizer(in.readLine());
            for(int y = 0; y < c; y++) {
                lab[x][y] = Integer.parseInt(st.nextToken());
                // 벽을 세울 수 있는 공간인지 체크하기 위해 바이러스 및 벽이 이미있는 구간은 방문처리
                if( lab[x][y] == 1 || lab[x][y] == 2 ) {
                    visit[x][y] = true;
                }
                // 바이러스 좌표 할당하기
                if(  lab[x][y] == 2 ) {
                    virus.add(new Point(x,y));
                }
            }
        }

        // 순열로 벽을 세울수 있는 공간의 좌표를 구한다.
        makeWall(0,lab);
        // 출력
        System.out.println(max);
    }

    private static void makeWall(int cnt ,int[][] lab) {
//        System.out.println("재귀 시작" + cnt);

        if( cnt == 3 ) {
            // 안전구간의 수
            int safe = 0;
            // 확산이 진행될 연구소 덤프
            int[][] map = new int[r][c];
            for(int x = 0; x < r; x++) {
                map[x] = Arrays.copyOf(lab[x],c);
            }
            // 벽 세우기
            for(int i = 0; i < 3; i++) {
                int row = mk[i][0];
                int col = mk[i][1];
                map[row][col] = 1;
            }

            // 바이러스 번식(확산)
            for( int j = 0; j < virus.size(); j++) {
                diff(virus.get(j), map);
            }

            // 안전지대 확인
            for(int x = 0; x < r; x++) {
                for(int y = 0; y < c; y++) {
                    if( map[x][y] == 0) {
                        safe++;
                    }
                }
            }
            // 안전지대 최댓값 비교
            max = Math.max(max,safe);
            return;
        }
        // 벽을 세울 수 있는 좌표의 경우의 수 구하기
        for(int x = 0; x < r; x++) {
            for(int y = 0; y < c; y++) {
                if( (lab[x][y] != 1 || lab[x][y] != 2) && !visit[x][y]) {
                    visit[x][y] = true;
                    mk[cnt] = new int[] {x,y};
                    makeWall(cnt + 1, lab);
                    visit[x][y] = false;
                }
            }
        }
    }

    private static void diff(Point start, int[][] map) {

        boolean[][] check = new boolean[r][c];
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(start);
        check[start.x][start.y] = true;

        while( !queue.isEmpty() ) {

            Point vi = queue.poll();
            map[vi.x][vi.y] = 2;        // 바이러스 감염

            for(int x = 0; x < 4; x++) {
                int nx = vi.x + dx[x];
                int ny = vi.y + dy[x];

                if( isCheck(nx,ny) && map[nx][ny] != 1 && !check[nx][ny] ) {
                    queue.offer(new Point(nx,ny));  // 연쇄감염원 추가
                    check[nx][ny] = true;          // 방문체크
                }
            }
        }
    }

    private static boolean isCheck(int x, int y) {
        if( x < 0  || x >= r || y < 0 || y >= c ) {
            return false;
        }
        return true;
    }
}

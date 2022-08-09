package swea;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 정사각형 방
public class D4_1861 {
    private static int N;
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {-1,1,0,0};
    private static int[][] sqare;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/sqareRoom_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./study_algorithm/output/swea/sqareRoom_input.txt"));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(in.readLine());            // 정사각형의 크기 N*N;
            int cnt = 0;                                    // 이동횟수
            int max = Integer.MIN_VALUE;
            int[][] sqare_cnt = new int[N+1][N+1];
            sqare = new int[N+1][N+1];                      // 정사각형 선언
            List<Integer> pos_room = new ArrayList<>();
            for(int i = 1; i <= N; i++) {                   // 각 방에 들어간 숫자 할당
                StringTokenizer st = new StringTokenizer(in.readLine());
                for(int j = 1; j <= N; j++) {
                    sqare[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 이동거리의 최댓값을 구하는 작업
            for(int x = 1; x <= N; x++) {
                for(int y = 1; y <= N; y++) {
                    cnt = room(x,y,1);
                    sqare_cnt[x][y] = cnt;
                    if( max < cnt ) {
                        max = cnt;
                    }
                }
            }
            // 이동거리가 최댓값과 같은 번호들을 추출
            for(int x = 1; x <= N; x++) {
                for(int y = 1; y <= N; y++) {
                    if( sqare_cnt[x][y] == max ) {
                        pos_room.add(sqare[x][y]);
                    }
                }
            }
            // 이동횟수가 같은 번호중에서 제일 낮은 번호를 추출
            int low = pos_room.stream().min((o1, o2) -> {
                if(o1.compareTo(o2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }).get();

            sb.append("#").append(t).append(" ").append(low + " " + max).append("\n");
        }
        System.out.println(sb);
        bos.write(sb.toString().getBytes());
        bos.flush();
        bos.close();
        in.close();
    }

    private static int room(int x, int y, int cnt) {

        // 4방탐색 시작
        for(int i = 0; i < 4; i++) {

            int nx = x + dx[i];     // 다음 x좌표
            int ny = y + dy[i];     // 다음 y좌표

            // 다음이동하려는 방이 정사각형을 벗어나지 않는다면
            if( isCheck(nx, ny)) {
                int diff = (sqare[nx][ny] - sqare[x][y]);
                if( diff == 1) {
                    return room(nx, ny, cnt + 1);
                }
            }
        }

        return cnt;
    }

    private static boolean isCheck(int x, int y) {
        if( x > 0 && x <= N & y > 0 & y <= N) {
            return true;
        } else {
            return false;
        }
    }
}

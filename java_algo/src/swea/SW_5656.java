package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_5656 {

    static class Wall implements Comparable<Wall>{
        int x;
        int y;
        int range;

        public Wall(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }

        @Override
        public int compareTo(Wall o) {
            return o.range - this.range;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wall wall = (Wall) o;
            return x == wall.x && y == wall.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    // 상하좌우
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static int n,r,c, max,col[];

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/crashWall2.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {

            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken()); // 떨어뜨릴 수 있는 구슬의 수
            c = Integer.parseInt(st.nextToken()); // 가로의 길이
            r = Integer.parseInt(st.nextToken()); // 세로의 길이
            max = 0;
            int count = 0;

            int[][] map = new int[r][c];
            col = new int[n];

            for(int x = 0; x < r; x++) {
                st = new StringTokenizer(in.readLine());
                for(int y = 0; y < c; y++) {
                    map[x][y] = Integer.parseInt(st.nextToken());
                    if( map[x][y] != 0) {
                        count++;
                    }
                }
//                System.out.println(Arrays.toString(map[x]));
            }

            dfs(0,map);

           sb.append("#").append(t).append(" ").append(count - max).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cnt, int[][] origin) {
        // 사용한 구슬을 전부 사용했을 경우 종료
        if( cnt == n) {
//            System.out.println(Arrays.toString(col));
            int result = 0;
            // 맵을 복사해준다.
            int[][] map = new int[r][c];
            for(int x = 0; x < r; x++) {
                map[x] = Arrays.copyOf(origin[x],c);
//                System.out.println(Arrays.toString(map[x]));
            }
            // 돌을 던져준다.
            for(int i = 0; i < n; i++) {
                // 제일 위에있는 돌을 구한다.
                int top = r - 1;
                for(int j = 0; j < r; j++) {
                    if ( map[j][col[i]] != 0 ) {
                        top = j;
                        break;
                    }
                }

//                System.out.println(" 행 : " + top + " 열" + col[i] + " 범위 : " + map[top][col[i]]);
                if( map[top][col[i]] == 0) continue;

                // 해당 열에 돌을 던져준다.
                result += crash(top,col[i],map);
//                System.out.println("확인");
//                for(int x = 0; x < r; x++) {
//                    System.out.println(Arrays.toString(map[x]));
//                }
                // 중력처리
                down(map);
            }
            max = Math.max(max,result);
            return;
        }

        for(int y = 0; y < c; y++) {
            col[cnt] = y;
            dfs(cnt + 1, origin);
        }
    }

    private static int crash(int row, int col, int[][] map) {

        Queue<Wall> queue = new ArrayDeque<>();
        queue.offer(new Wall(row,col,map[row][col]));
        map[row][col] = 0;
        int count = 0;

        while ( !queue.isEmpty() ) {

            Wall wall = queue.poll();
            count++;
            // 범위가 1이 아니라면 탐색
            if( wall.range > 1) {
                // 4방탐색
                for( int i = 1; i <= wall.range - 1; i++) {
                    for (int x = 0; x < 4; x++) {
                        int nx = wall.x + (dx[x] * i);
                        int ny = wall.y + (dy[x] * i);

                        if (isValid(nx, ny) && map[nx][ny] != 0) {
                            queue.offer(new Wall(nx, ny, map[nx][ny]));
                            map[nx][ny] = 0;
                        }
                    }
                }
            }
        }

        return count;
    }

    private static boolean isValid(int x, int y) {
        if( x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }
        return true;
    }

    private static void down(int[][] map) {

        for(int y = 0; y < c; y++) {
            int x = r - 1;
            while ( x > 0 ) {
                if( map[x][y] == 0) {
                    for(int z = x - 1; z >= 0; z--) {
                        if( map[z][y] != 0) {
                            int tmp = map[z][y];
                            map[z][y] = map[x][y];
                            map[x][y] = tmp;
                            break;
                        }
                    }
                }
                x--;
            }
        }
    }
}

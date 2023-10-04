import java.io.*;
import java.util.*;

class Solution {
    
    // 인수를 줄이기 위해서 가로,세로는 전역변수로 선언
    private static int w, h;
    // 4방향 탐색을 위한 이차원배열 북, 동, 남, 서
    private static int[][] direct = new int[][] {
        {-1,0},{0,1},{1,0},{0,-1}
    };
    
    public int solution(String[] maps) {
        int answer = 0;
        // 가로 세로 길이를 구한다.
        w = maps.length;
        h = maps[0].length();
        // 시작지점 초기화
        int[] start = new int[3];
        // 레버위치 초기화
        int[] lever = new int[3];
        // 맵에서 시작지점과 레버좌표를 할당해준다.
        for(int r = 0; r < w; r++) {
            for(int c = 0; c < h; c++) {
                switch(maps[r].charAt(c)) {
                    case 'S':
                        start[0] = r;
                        start[1] = c;
                        break;
                    case 'L':
                        lever[0] = r;
                        lever[1] = c;
                        break;
                }
            }
        }
        // 출발지점 -> 레버?까지의 최단거리를 구한다. A
        // 최단거리는 BFS로 구한다.
        int distanceA = bfs(start, 'L', maps);
        // System.out.println(distanceA + " 입니다.");
        // 레버 -> 출구까지의 최단거리를 구한다. B
        // 최단거리는 BFS로 구한다.
        int distanceB = bfs(lever, 'E', maps);
        // System.out.println(distanceB + " 입니다.");
        // 만약 A or B가 하나라도 -1 이라면 나갈 수 없음 = -1 출력
        if( distanceA == -1 || distanceB == -1 ) {
            answer = -1;
        } else { // A + B를 더한다.
            answer = distanceA + distanceB;
        }
        return answer;
    }
    
    /**
    * @param : 출발 좌표, 도착지점(문자), 지도
    */
    private static int bfs(int[] start, char goal, String[] maps) {
        // 좌표를 저장할 큐
        Queue<int[]> que = new LinkedList();
        // 시작지점 저장
        que.offer(start);
        // 순환을 방지하기 위한 방문체크 이차원배열
        boolean[][] visited = new boolean[w][h];
        // 출발지점 방문체크
        visited[start[0]][start[1]] = true;
        while(!que.isEmpty()) {
            int[] el = que.poll();
            // 만약 goal지점에 도착했으면 거리를 반환하고 종료
            if(maps[el[0]].charAt(el[1]) == goal) {
                return el[2];
            }
            // System.out.printf("이동 좌표 : [%d,%d,%d] 이동공간 : %c\n",el[0],el[1],el[2],maps[el[0]].charAt(el[1]));
            // 4방향 탐색
            for(int i = 0, size = direct.length; i < size; i++) {
                // 이동한 좌표
                int nx = el[0] + direct[i][0];
                int ny = el[1] + direct[i][1];
                // 이동범위 확인 ( 벗어나면 다음 좌표확인 )
                if(isCheck(nx, ny)) continue;
                // 방문한 적인 없어야함 && 벽(X)이 아니여야함
                if( !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    // 방문처리
                    visited[nx][ny] = true;
                    // 큐에 넣는다.
                    que.offer(new int[] {nx, ny, el[2] + 1});
                }
            }
        }
        return -1;
    }
    
    /**
    * 좌표의 범위를 체크하는 메소드
    */
    private static boolean isCheck(int x, int y) {
        if( x < 0 || x >= w || y < 0 || y >= h ) {
            return true;
        }
        return false;
    }
}
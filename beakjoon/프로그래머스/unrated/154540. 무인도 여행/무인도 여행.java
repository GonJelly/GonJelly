import java.util.*;

class Solution {
    
    private static int r, c;
    private static boolean[][] visited;
    // 방향이동 { 상, 우, 하, 좌 }
    private static final int[][] way = {
        {-1,0},{0,1},{1,0},{0,-1}
    };
    
    public int[] solution(String[] maps) {
        
        int[] answer = {};
        int size = answer.length;
        
        r = maps.length;        // 세로의 길이
        c = maps[0].length();   // 가로의 길이
        // 해당 위치를 탐색여부 판단 2차원배열
        visited = new boolean[r][c];
        
        // System.out.printf("지도의 크기 : 세로 : %d 가로 : %d \n",r,c);
        
        // 해당 maps을 탐색
        for(int v = 0; v < r; v++) {
            for(int h = 0; h < c; h++) {
                // X ==> 바다인지 확인
                char ch = maps[v].charAt(h);
                // System.out.printf("해당 좌표의 값 : [%d,%d] : %c\n",v,h,ch);
                // 해당 지점에서 넓이우선 탐색(bfs) start
                if(ch != 'X' & !visited[v][h]) {
                    int total = bfs(new int[] {v,h}, maps);
                    answer = Arrays.copyOf(answer, size + 1);
                    answer[size++] = total;
                }
            }
        }
        
        if(size == 0) {
            answer = new int[] {-1};
        } else {
            Arrays.sort(answer);
        }
        
        return answer;
    }
    
    private static int bfs(int[] start, String[] maps) {
        
        Queue<int[]> que = new LinkedList();
        que.offer(start);                       // 시작지점
        visited[start[0]][start[1]] = true;     // 방문
        // 무인도 체류시간
        int total = Integer.parseInt(String.valueOf(maps[start[0]].charAt(start[1])));
        
        while(!que.isEmpty()) {
            int[] pos = que.poll();
            int x = pos[0];         // 세로
            int y = pos[1];         // 가로
            // System.out.printf("방문한 위치 : [%d,%d] : 값 : %c\n",x,y,maps[x].charAt(y));
            // 사방탐색
            for(int i = 0; i < 4; i++) {
                int nr = x + way[i][0];         // 세로 이동
                int nc = y + way[i][1];         // 가로 이동
                if(!checked(nr,nc)) continue;   // 범위를 벗어나면 다음 확인
                // 바다가 아니고 방문(ture)한 적이 없어야 한다.
                if(maps[nr].charAt(nc) != 'X' & !visited[nr][nc]) {
                    visited[nr][nc] = true;             // 방문
                    que.offer(new int[] {nr,nc});      // 탐색 저장
                    total += Integer.parseInt(String.valueOf(maps[nr].charAt(nc)));
                }
            }
        }
        return total;
    }
    
    private static boolean checked(int x, int y) {
        if( x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }
        return true;
    }
}
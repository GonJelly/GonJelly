package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1058 {

    static int[][] friends;
    static int T;
    public static void main(String[] args) throws IOException {

        long start = System.nanoTime();
        // 입력받는 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int max = Integer.MIN_VALUE;
        // 친구 관계를 이차배열로 표현 0: 친구아님 1: 직접친구
        friends = new int[T][T];

        for(int y = 0; y < T; y++) {
            String[] token = br.readLine().split("");
            for(int x = 0; x < T; x++) {
                String check = token[x];
                if( check.equals("N") ) {
                    friends[y][x] = 0;
                } else {
                    friends[y][x] = 1;
                }
            }
        }

//        System.out.println(bfs(0,visit,0));
        // 각 친구 수를 비교해서 제일 많은 친구의 수를 출력!
        for(int i = 0; i < T; i++) {
            boolean[] visit = new boolean[T];
            max = Math.max(max,bfs(i,visit,0));
        }

        System.out.println(max);
        long end = System.nanoTime();
        System.out.println( ( end - start ) / 1_000_000_000 + "s");
        br.close();
    }

    private static int bfs(int friend,boolean[] visit , int count) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {friend,0});
        visit[friend] = true;

        // 친구를 전부 확인
        while( !queue.isEmpty() ) {

            int[] tmp = queue.poll();
            // 친구의 친구까지만 확인
            if( tmp[1] == 2) continue;
            for(int i = 0; i < T; i++) {
                if( !visit[i] && friends[tmp[0]][i] == 1) {
                    queue.offer(new int[] {i,tmp[1] + 1});
                    visit[i] = true;
                    count++;
                }
            }
        }

        return count;
    }
}

package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697 {

    private static int min;
    private static boolean[] isvisit;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Queue<int[]> que = new LinkedList<>();
        isvisit = new boolean[100001];

        int n = Integer.parseInt(st.nextToken());       // 수빈이의 점
        int k = Integer.parseInt(st.nextToken());       // 동생의 점
        min   = Integer.MAX_VALUE;

        bfs(que, n, k);
        System.out.println(min);
    }

    private static void bfs(Queue<int[]> que ,int start ,int end) {

        que.offer(new int[]{start,0});

//        System.out.println(que.size() + "   in");
        while ( !que.isEmpty() ) {

            int[] node = que.poll();
//            System.out.println(Arrays.toString(node));
            if( node[0] == end ) {
                min = node[1];
                return;
            }

            if( node[0] + 1 < 100001 && !isvisit[node[0] + 1] ) {
                que.offer(new int[] {node[0] + 1,node[1] + 1});
                isvisit[node[0] + 1] = true;
            }
            if( node[0] - 1 >= 0 &&!isvisit[node[0] - 1] ) {
                que.offer(new int[] {node[0] - 1,node[1] + 1});
                isvisit[node[0] - 1] = true;
            }
            if( node[0] * 2 < 100001  && !isvisit[node[0] * 2]) {
                que.offer(new int[] {node[0] * 2,node[1] + 1});
                isvisit[node[0] * 2] = true;
            }

        }
    }
}

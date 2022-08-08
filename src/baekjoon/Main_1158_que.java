package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 1 <= N <= 5,000 */
/*	256 MB */
public class Main_1158_que {

    public static void main(String[] args) throws IOException {

//        FileInputStream file = new FileInputStream("");
//        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());                               // 사람 수
        int k = Integer.parseInt(st.nextToken());                               // k번째 사람
        Queue<Integer> q = new LinkedList<>();                                             // 삭제된 순서

        for(int i = 1; i <= n; i++) {
            q.offer(i);
        }
        sb.append("<");
        while ( q.size() > 1 ) {
            for(int j = 1; j <= k - 1; j++ ) {
                q.add(q.poll());
            }
            sb.append(+q.poll() + ", ");
        }

        sb.append(q.poll()+">");
        System.out.println(sb);
    }
}

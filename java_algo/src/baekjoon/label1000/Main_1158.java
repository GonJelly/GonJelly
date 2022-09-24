package baekjoon.label1000;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 1 <= N <= 5,000 */
/*	256 MB */
public class Main_1158 {

    static boolean[] cycle;
    static Queue<Integer> q;
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {

//        FileInputStream file = new FileInputStream("");
//        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());                               // 사람 수
        k = Integer.parseInt(st.nextToken());                               // k번째 사람
        cycle = new boolean[n + 1];                                         // 삭제여부 확인
        q = new LinkedList<>();                                             // 삭제된 순서
        delete(0,0);                                             // 요세푸스 순열 시작

        System.out.println(q.toString().replace("[","<").replace("]",">"));
//        sb.append("<");
//        while( !q.isEmpty() ) {
//            sb.append(" " + q.poll());
//        }
//        sb.append(" >");
//        System.out.println(sb);
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(""));
    }

    private static void delete(int person, int cnt) {

        if( q.size() == n) {
            return;
        }

        while ( cnt != k ) {

            if( person >= n) {
                person = 0;
            }

            if( !cycle[++person] ) {
                cnt++;
//                System.out.println(person + " : " + cnt);
            }

        }
//        System.out.println();

        cycle[person] = true;
        q.offer(person);
        delete(person,0);
    }
}

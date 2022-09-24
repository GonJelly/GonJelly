package baekjoon;

import java.io.*;
import java.util.*;

public class Main_12891 {

    static int total, cntA,cntC,cntG,cntT;

    /* (1 ≤ |P| ≤ |S| ≤ 1,000,000) */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/DNA_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Queue<Character> q = new LinkedList<>();

        int s = Integer.parseInt(st.nextToken()); // 문자열의 길이
        int p = Integer.parseInt(st.nextToken()); // 부분문자열의 길이

        char[] string = in.readLine().toCharArray(); // 문자열 입력받기

        st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());   // a의 최소 갯수
        int c = Integer.parseInt(st.nextToken());   // c의 최소 갯수
        int g = Integer.parseInt(st.nextToken());   // g의 최소 갯수
        int t = Integer.parseInt(st.nextToken());   // t의 최소 갯수

        for(int i = 0; i < s; i++) {
            switch( string[i] ) {
                case 'A':
                    cntA++;
                    break;
                case 'C':
                    cntC++;
                    break;
                case 'G':
                    cntG++;
                    break;
                case 'T':
                    cntT++;
                    break;
                default:
                    System.out.println(0);
                    System.exit(0);
            }

            q.offer(string[i]);  // 차례대로 저장

            if( i > p - 1) {
                switch( q.poll() ) {
                    case 'A':
                        cntA--;
                        break;
                    case 'C':
                        cntC--;
                        break;
                    case 'G':
                        cntG--;
                        break;
                    case 'T':
                        cntT--;
                        break;
                }
            }
            // 부분문자열 p만큼 길이를 확인하면 유효성 체크
            if( i >= p) {
                if (cntA >= a && cntC >= c && cntG >= g && cntT >= t) {
                    total++;
                }
            }
        }
        System.out.println(total);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + "초");
    }
}


package baekjoon;

import java.io.*;
import java.util.*;

public class Main_12891 {

    static int total,p,A,C,G,T;           // 비밀번호의 갯수\
    static int[] dan_cnt = {0,0,0,0};
    static char[] source;
    /* (1 ≤ |P| ≤ |S| ≤ 1,000,000) */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("./res/baekjoon/DNA_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        // 문자열의 길이 , 부분문자열의 길이
        int s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        // DNA 문자열
        source = in.readLine().toCharArray();
        st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= s-p; i++) {
            dna(i,0);
            dan_cnt = new int[] {0,0,0,0};
        }

        System.out.println(total);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + "초");
    }

    private static void dna(int idx, int cnt) {
        if( cnt == p ) {
            if(dan_cnt[0] >= A & dan_cnt[1] >= C &
                    dan_cnt[2] >= G & dan_cnt[3] >= T) {
                total++;
            }
            return;
        }
        if( source[idx] == 'A') dan_cnt[0]++;
        if( source[idx] == 'C') dan_cnt[1]++;
        if( source[idx] == 'G') dan_cnt[2]++;
        if( source[idx] == 'T') dan_cnt[3]++;

        dna(idx+1,cnt+1);
    }
}

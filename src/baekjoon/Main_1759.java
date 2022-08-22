package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1759 {

    private static int L,C;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        // 암호의 입력값을 받아온다.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        sb = new StringBuilder();

        L = Integer.parseInt(st.nextToken()); // 암호문의 길이
        C = Integer.parseInt(st.nextToken()); // 암호문에 사용된 문자들 갯수
        // 암호문자 저장하기
        char[] syntex = in.readLine().replaceAll(" ","").toCharArray();
        boolean[] isCheck = new boolean[C];


        // 받아온 입력값을 정렬해준다.
        Arrays.sort(syntex);
        // 조합으로 암호문생성
        combi(0,0,isCheck,syntex);

        System.out.print(sb);
    }

    private static void combi(int idx, int cnt, boolean[] isCheck, char[] syntex) {

        if( cnt == L ) {
            StringBuilder temp = new StringBuilder();
            for(int i = 0; i < C; i++) {
                if( isCheck[i] ) {
                    temp.append(syntex[i]);
                }
            }

            if( check(temp.toString())) {
                sb.append(temp).append("\n");
            }
        }

        for(int i = idx; i < C; i++) {
            isCheck[i] = true;
            combi(i + 1, cnt + 1, isCheck, syntex);
            isCheck[i] = false;
        }
    }

    private static boolean check(String temp) {

        int m = 0;
        int j = 0;

        for(int i = 0 ,length = temp.length(); i < length; i++) {
            if( "aeiou".contains(temp.charAt(i)+"") ) {
                m++;
            } else if( "bcdfghjklmnpqrstvwxyz".contains(temp.charAt(i)+"")) {
                j++;
            }
        }

        if( m < 1 || j < 2) return false;
        else return true;
    }
}

package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1759_np {

    private static int L,C;
    private static List<String> list;

    public static void main(String[] args) throws IOException {

        // 암호의 입력값을 받아온다.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        list = new ArrayList<>();

        L = Integer.parseInt(st.nextToken()); // 암호문의 길이
        C = Integer.parseInt(st.nextToken()); // 암호문에 사용된 문자들 갯수
        // 암호문자 저장하기
        char[] syntex = in.readLine().replaceAll(" ","").toCharArray();
        int[] select = new int[C];
        Arrays.fill(select,0,L,1);
        // 받아온 입력값을 정렬해준다.
        Arrays.sort(syntex);

        // 조합으로 암호문생성 ( 조합-np )
        do {
            expression(select,syntex);
        } while( np(select));

        Collections.sort(list);
//        System.out.println(count);
        for(int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j));
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

    private static void expression(int[] select, char[] syntex) {
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < C; i++) {
            if( select[i] == 1) temp.append(syntex[i]);
        }

        if( check(temp.toString())) {
            list.add(temp.toString());
        }
    }

    private static boolean np(int[] isCheck) {

        int i = 0;
        while (i < C - 1 && isCheck[i] <= isCheck[i+1] ) ++i;

        if( i == C - 1 ) return false;

        int j = 0;
        while(isCheck[i+1] >= isCheck[j]) ++j;

        swap(isCheck, i+1, j);

        int k = 0;
        while( i > k) swap(isCheck, i--,k++);

        return true;
    }

    private static void swap(int[] isCheck, int i, int j) {

        int temp = isCheck[i];
        isCheck[i] = isCheck[j];
        isCheck[j] = temp;

    }
}

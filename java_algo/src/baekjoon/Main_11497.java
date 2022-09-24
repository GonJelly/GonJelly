package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11497 {

    static int wood;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());       // 테스트 케이스의 갯수

        for(int t = 1; t <= T; t++) {

            wood = Integer.parseInt(in.readLine());     // 통나무의 갯수
            int min = Integer.MAX_VALUE;                // 최소 난이도 저장
            int[] height = new int[wood];               // 통나무의 높이

            st = new StringTokenizer(in.readLine());
            for ( int i = 0; i < wood; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            // 1차 작업 정렬 ( 오름차순 )
            Arrays.sort(height);

            //2차 작업 순열을 만들어준다.
            do {
                int dept = 0;
                int maxHeight =  -1;
                // 최대 높이를 구한다. ( 즉, 난이도 구하기 )
                for(int i = 0; i < wood; i++) {
                    int tmp = i - 1 < 0 ? wood - 1 : i - 1;
                    dept = Math.abs( height[tmp] - height[i] );
                    if( dept > maxHeight ) {
                        maxHeight = dept;
                    }
                }
                // 최소 난이도 구하기
                if( min > maxHeight ) {
                    min = maxHeight;
                }
            } while ( np(height) );

            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean np(int[] height) {

        int i = wood - 1;
        while( i > 0 && height[i] <= height[i - 1] ) i--;

        if( i == 0 ) return false;

        int j = wood - 1;
        while ( height[i - 1] >= height[j] ) j--;

        swap(height, i - 1, j);

        int k = wood - 1;
        while ( i < k ) swap(height,i++,k--);

        return true;
    }

    private static void swap(int[] height, int i, int j) {

        int temp = height[j];
        height[j] = height[i];
        height[i] = temp;

    }
}

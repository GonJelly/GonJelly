package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15686 {
    // 시간제한 : 1초
    // 메모리 512MB
    // 2<= N <= 50
    // 1 <= M <= 13

    private static int N, M, result;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/chicken_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        result = Integer.MAX_VALUE;
        int idx_chicken = 0; int idx_home = 0;

        int[][] city = new int[N+1][N+1];           // 도시 선언
        for(int i = 1; i <= N; i++) {                // 도시 할당
            token = new StringTokenizer(in.readLine());
            for(int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(token.nextToken());
                if ( city[i][j] == 1 ) idx_home++;
                if ( city[i][j] == 2 ) idx_chicken++;
            }
        }
        // 도시 할당 확인 start
//        for(int i = 1; i <= N; i++) {
//            for(int j = 1; j<= N; j++) {
//                System.out.print(city[i][j] + " ");
//            }
//            System.out.println();
//        }
        // 도시 할당 확인 end

        int[][] chicken = new int[idx_chicken][];
        int[][] home = new int[idx_home][];

        idx_home = 0; idx_chicken = 0;              // 인덱스 초기화

        for(int i = 1; i <= N; i++) {                // 치킨집, 집 좌표 할당
            for(int j = 1; j <= N; j++) {
                if( city[i][j] == 1) home[idx_home++] = new int[] {i,j};
                if( city[i][j] == 2) chicken[idx_chicken++] = new int[] {i,j};
            }
        }
        // 집, 치킨집 좌표 확인 start
//        for(int i = 0; i < idx_chicken; i++) {
//            System.out.println(Arrays.toString(chicken[i]));
//        }
//        System.out.println("============================");
//        for(int i = 0; i < idx_home; i++) {
//            System.out.println(Arrays.toString(home[i]));
//        }
        // 집, 치킨집 좌표 확인 end

        int[] isCheck = new int[idx_chicken];
        for(int i = idx_chicken - 1; i >= idx_chicken - M; i--) {
            isCheck[i] = 1;
        }
        // next_permutation 이용한 치킨 구하기
        do {
//            System.out.println(Arrays.toString(isCheck));
            chickenRoad(isCheck, chicken, home);
        } while(np(isCheck, idx_chicken));

        System.out.println(result);
    }

    private static boolean np(int[] isCheck, int idx_chicken) {

        int i = idx_chicken - 1;
        while (i > 0 && isCheck[i - 1] >= isCheck[i] ) --i;

        if( i == 0 ) return false;

        int j = idx_chicken - 1;
        while( isCheck[i-1] >= isCheck[j] ) --j;

        swap(isCheck, i-1, j);

        int k = idx_chicken - 1;
        while ( i < k ) swap(isCheck, i++, k--);

        return true;
    }

    private static void swap(int[] isCheck, int i, int k) {
        int temp = isCheck[i];
        isCheck[i] = isCheck[k];
        isCheck[k] = temp;
    }

    private static void chickenRoad(int[] isCheck, int[][] chicken, int[][] home) {
        int distance = 0;
        for(int i = 0; i < home.length; i++ ) {
            int min = Integer.MAX_VALUE;
            for ( int j = 0; j < chicken.length; j++) {
                if( isCheck[j] == 1) {
                    int x = Math.abs(home[i][0] - chicken[j][0]);
                    int y = Math.abs(home[i][1] - chicken[j][1]);
                    min = Math.min(min, x + y );
                }
            }
            distance += min;
        }
        result = Math.min(result,distance);
    }
}

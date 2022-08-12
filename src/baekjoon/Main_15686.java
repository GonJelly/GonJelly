package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main_15686 {

    private static int N,M,min;
    private static int[][] city;
    private static List<int[]> stores;
    private static List<int[]> homes;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./res/baekjoon/chicken_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        long start = System.nanoTime();
        N = Integer.parseInt(tokens.nextToken());               // 도시의 크기
        M = Integer.parseInt(tokens.nextToken());               // 최대 고를 수 있는 치킨집
        stores = new LinkedList<>();
        homes = new LinkedList<>();
        min = Integer.MAX_VALUE;

        city = new int[N+1][N+1];                               // 도시 선언 ( 좌표가 1,1부터 시작이라서 N+1로 선언해줌
        for(int i = 1; i <= N; i++) {                           // 빈칸, 치킨집, 집 할당
            tokens = new StringTokenizer(in.readLine());
            for(int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        // 집의 좌표, 가게의 좌표
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if( city[i][j] == 2) {
                    stores.add(new int[]{i,j});
                } else if( city[i][j] == 1) {
                    homes.add(new int[]{i,j});
                }
            }
        }
//        for(int[] arr : stores) {
//            System.out.print(Arrays.toString(arr));
//        }
//        System.out.println();
//        for(int[] arr : homes) {
//            System.out.print(Arrays.toString(arr));
//        }
        getDistance(0,0);
        System.out.println(min);
        long end = System.nanoTime();
        System.out.println((end - start ) / 100000 + "s");
        in.close();
    }

    private static void getDistance(int cnt,int flag) {

        if ( cnt == M ) {
            int total = 0;
            for(int i = 0; i < homes.size(); i++) {
                int distance = Integer.MAX_VALUE;
                for (int j = 0; j < stores.size(); j++) {
                    if ( (flag & 1<<j ) != 0) continue;
                    int sum = Math.abs(homes.get(i)[0] - stores.get(j)[0]) + Math.abs(homes.get(i)[1] - stores.get(j)[1]);
                    distance = Math.min(distance, sum);
                }
                total += distance;
            }
            min = Math.min(min,total);
            return;
        }

        for(int i = 0; i < stores.size(); i++) {
            if( (flag & 1<<i) != 0) continue;
            getDistance(cnt + 1, flag | 1<<i);
        }
    }

}

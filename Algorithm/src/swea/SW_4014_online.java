package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_4014_online {

    static int n, len;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/sw_4014.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int TC = Integer.parseInt(st.nextToken());

        for( int t = 1; t <= TC; t++ ) {

            st = new StringTokenizer(in.readLine());

            n = Integer.parseInt(st.nextToken()); // 토지의 면적
            len = Integer.parseInt(st.nextToken()); // 경사로의 길이

            int[][] map1 = new int[n][n];
            int[][] map2 = new int[n][n];

            for( int r = 0; r < n; r++) {
                st = new StringTokenizer(in.readLine());
                for( int c = 0; c < n; c++) {
                    map1[r][c] = map2[c][r] = Integer.parseInt(st.nextToken());
                }
            }

            process(map1,map2);
        }

    }

    private static void process(int[][] map1, int[][] map2) {

        int count = 0;

        for(int i = 0; i < n; i++) {
            if( valiable(map1[i]) ) count++;
            if( valiable(map2[i]) ) count++;
        }

        System.out.println(count);
    }

    private static boolean valiable(int[] road) {

        int beforeHeight = road[0];
        int size = 0;
        int j = 0;

        while( j < n) {
            if( beforeHeight == road[j] ) {
                size++;
                j++;
            } else if ( beforeHeight + 1 == road[j] ) {
                if( size < len ) return false;

                beforeHeight++;
                size = 1;
                j++;
            } else if( beforeHeight - 1 == road[j] ) {
                int count = 0;
                for( int k = j; k < n; k++ ) {
                    if( beforeHeight - 1 != road[k] ) return false;

                    if( ++count == len ) break;
                }

                if( count < len ) return false;

                beforeHeight--;
                j += len;
                size = 0;
            } else {
                return false;
            }
        }

        return true;
    }

}

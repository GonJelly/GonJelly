package baekjoon.label1000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author 박준영
 */
public class Main_1992 {
    private static int[][] target;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/square_tree.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());

        target = new int[N][N];
        for(int i = 0; i < N; i++) {
            target[i] = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        encoding(0,0,N,N );
        System.out.println(sb);
        in.close();
    }

    private static void encoding(int s1, int s2, int e1 , int e2) {

        int checksum = isCheck(s1,s2,e1,e2);
        if( checksum != -1) {
            sb.append(checksum);
            return;
        }

        sb.append("(");

        int mid1 = (e1 + s1) / 2;
        int mid2 = (e2 + s2) / 2;
        if( e1 - s1 > 2 && e2 - s2 > 2) {
            encoding(s1, s2, mid1, mid2);          // 1사면
            encoding(s1, mid2, mid1, e2);          // 2사면
            encoding(mid1, s2, e1, mid2);          // 3사면
            encoding(mid1, mid2, e1, e2);          // 4사면
        } else {
            for( int i = s1; i < e1; i++) {
                for(int j = s2; j < e2; j++) {
                    sb.append(target[i][j]);
                }
            }
        }
        sb.append(")");
    }

    private static int isCheck(int s1, int s2, int e1 , int e2) {

        int one = 0;
        int zero = 0;

        for( int i = s1; i < e1; i++) {
            for(int j = s2; j < e2; j++) {
                if( target[i][j] == 1 ) {
                    one++;
                } else {
                    zero++;
                }
            }
        }

        int compare = (int) Math.pow(e2 - s2,2);
        if( one == compare ) {
            return 1;
        } else if( zero == compare) {
            return 0;
        } else {
            return -1;
        }
    }
}

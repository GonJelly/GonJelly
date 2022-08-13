package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1931 {

    private static int n;
    private static int[][] conference;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/conference_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());

        conference = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            conference[i][0] = Integer.parseInt(st.nextToken().trim()); // 시작 시간
            conference[i][1] = Integer.parseInt(st.nextToken().trim()); // 종료 시간
        }

        for(int j = 0; j < n; j++) {
            System.out.println(Arrays.toString(conference[j]));
        }

        for(int i = 1; i <= n; i++) {
            int[] valiable = new int[n];
            for(int j = n-1; j > n-1-i; j--) {
                valiable[j] = 1;
            }

            do{
                System.out.println(Arrays.toString(valiable));
            } while(np(valiable));
        }

    }

    private static boolean np(int[] valiable) {

        int i = n - 1;
        while (i > 0 && valiable[i-1] >= valiable[i]) --i;

        if( i == 0) return false;

        int j = n - 1;
        while (valiable[i-1] > valiable[j]) --j;

        swap(valiable, i-1, j);

        int k = n - 1;
        while ( i < k) swap(valiable, i++, k--);

        return true;
    }

    private static void swap(int[] valiable, int i, int k) {
        int temp = valiable[i];
        valiable[i] = valiable[k];
        valiable[k] = temp;
    }
}

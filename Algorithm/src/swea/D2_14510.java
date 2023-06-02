package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D2_14510 {

    static int max, n, tree[], min;
    static int[][] dp;
    static boolean[] visit;
    static int[] store;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/tree_input1.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());       // 나무의 수

        }

        System.out.println(sb);
    }
}

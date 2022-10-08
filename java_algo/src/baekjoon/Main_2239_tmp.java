package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2239_tmp {

    static boolean[][] horizen, verticle;
    static int[][] end;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("res/baekjoon/sdoku.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[][] sdoku = new int[9][9];
        horizen = new boolean[9][10];
        verticle = new boolean[9][10];

        for(int r = 0; r < 9; r++) {
            char[] tmp = in.readLine().toCharArray();
            for(int c = 0; c < 9; c++ ) {
                int num = tmp[c] - '0';
                sdoku[r][c] = num;
                horizen[r][num] = true;
                verticle[c][num] = true;
            }
        }


    }
}

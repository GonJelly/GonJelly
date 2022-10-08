package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2239 {

    static boolean[][] horizen, verticle;
    static int[][] end;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("res/baekjoon/sdoku.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[][] sdoku = new int[10][10];
        end = new int[10][10];
        horizen = new boolean[10][10];
        verticle = new boolean[10][10];

        for(int r = 1; r < 10; r++) {
            char[] tmp = in.readLine().toCharArray();
            for(int c = 1; c < 10; c++ ) {
                int num = tmp[c - 1] - '0';
                sdoku[r][c] = num;
                horizen[r][num] = true;
                verticle[c][num] = true;
            }
        }

        for(int i = 0; i < 10; i++) {
            Arrays.fill(end[i],10);
        }

        boolean[][] squar = subset(10,10,sdoku);
        dfs(1,1,sdoku, squar);

        for(int i = 1; i < 10; i++) {
            for(int j = 1; j < 10; j++) {
                System.out.print(end[i][j]);
            }
            System.out.println();
        }
    }

    private static void dfs(int r, int c, int[][] sdoku, boolean[][] squar) {

        if( r == 9 && c == 10) {
            // 즉, 81자리의 수가 제일 작은 경우를 출력한다.
            if( end[9][9] > sdoku[9][9] ) {
                for(int i = 0; i < 10; i++) {
                    end[i] = Arrays.copyOf(sdoku[i],10);
                }
            }
            return;
        }

        // 범위를 벗어나면 다음 행으로 이동
        if( c >= 10) {
            dfs(r + 1, 1, sdoku, squar);
            return;
        }

        // 0이 아니면 다음으로 이동
        if( sdoku[r][c] != 0 ) {
            dfs(r, c + 1, sdoku, squar);
            return;
        }

        for( int num = 1; num < 10; num++) {

            if( !horizen[r][num] && !verticle[c][num] ) {
                horizen[r][num] = true;
                verticle[c][num] = true;
                sdoku[r][c] = num;

                if( r <= 3) {
                    if( c <= 3 ) {
                        if ( !squar[1][num] ) {
                            squar[1][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[1][num] = false;
                        }
                    } else if ( c <= 6 ) {
                        if ( !squar[2][num] ) {
                            squar[2][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[2][num] = false;
                        }
                    } else if ( c <= 9) {
                        if ( !squar[3][num] ) {
                            squar[3][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[3][num] = false;
                        }
                    }
                } else if ( r <= 6 ) {
                    if( c <= 3 ) {
                        if ( !squar[4][num] ) {
                            squar[4][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[4][num] = false;
                        }
                    } else if ( c <= 6 ) {
                        if ( !squar[5][num] ) {
                            squar[5][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[5][num] = false;
                        }
                    } else if ( c <= 9) {
                        if ( !squar[6][num] ) {
                            squar[6][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[6][num] = false;
                        }
                    }
                } else if ( r <= 9 ) {
                    if( c <= 3 ) {
                        if ( !squar[7][num] ) {
                            squar[7][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[7][num] = false;
                        }
                    } else if ( c <= 6 ) {
                        if ( !squar[8][num] ) {
                            squar[8][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[8][num] = false;
                        }
                    } else if ( c <= 9) {
                        if ( !squar[9][num] ) {
                            squar[9][num] = true;
                            dfs(r, c + 1, sdoku, squar);
                            squar[9][num] = false;
                        }
                    }
                }

                // 초기화
                sdoku[r][c] = 0;
                verticle[c][num] = false;
                horizen[r][num] = false;
            }

        }

    }

    private static boolean[][] subset(int r, int c, int[][] sdoku) {
        boolean[][] squar = new boolean[10][10];

        // 1사분면
        squar[1] = arr(r%3,c%3,sdoku);
        // 2사분면
        squar[2] = arr(r%3,c%3 + 3,sdoku);
        // 3사분면
        squar[3] = arr(r%3,c%3 + 6,sdoku);
        // 4사분면
        squar[4] = arr(r%3 + 3,c%3,sdoku);
        // 5사분면
        squar[5] = arr(r%3 + 3,c%3 + 3,sdoku);
        // 6사분면
        squar[6] = arr(r%3 + 3,c%3 + 6,sdoku);
        // 7사분면
        squar[7] = arr(r%3 + 6,c%3,sdoku);
        // 8사분면
        squar[8] = arr(r%3 + 6,c%3 + 3,sdoku);
        // 8사분면
        squar[9] = arr(r%3 + 6,c%3 + 6,sdoku);

        return squar;
    }

    private static boolean[] arr(int r, int c, int[][] sdoku) {

        boolean[] sub = new boolean[10];
        for(int x = r; x < r + 3; x++) {
            for(int y = c; y < c + 3; y++) {
                sub[sdoku[r][c]] = true;
            }
        }
        return sub;
    }

}

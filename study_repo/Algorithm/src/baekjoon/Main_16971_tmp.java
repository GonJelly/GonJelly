package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16971_tmp {

    static int max, n, m, idx;
    static int[][] arrayA;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        List<int[][]> combination = new ArrayList<>();

        // 행,열 값 받아오기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 배열A 할당하기
        arrayA = new int[n][m];
        idx = -1;
        max = 0;

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < m; c++) {
                arrayA[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfsh(0,0, 0, true);
        dfsv(0,0, 0, true);
//        printArr(arrayA);
        System.out.println(max);
    }

    private static void dfsh(int cnt, int total, int flag,boolean isChanged) {

        if( cnt == m ) {
//            System.out.println("dfsh : " + total);
            max = Math.max(max,total);
            return;
        }
//        System.out.println(cnt + " : " + total);
        for( int c = 0; c < m; c++ ) {
            // 사용한 적이 있다면 사용하지 않음
            if( (flag & (1<<c)) != 0 ) continue;
            // 초기화
            int sum = 0;
            // 이미 교환했다면 해당 열에 맞지않으면
            if( !isChanged ) {
                if ( (flag & (1<<cnt)) == 0) {
                    for (int r = 0; r < n; r++) {
                        if (r == 0 || r == n - 1) {
                            sum += arrayA[r][cnt];
                        } else {
                            sum += 2 * arrayA[r][cnt];
                        }
                    }

                    if (cnt == 0 || cnt == m - 1) {
                        dfsh(cnt + 1, total + sum, flag | (1 << cnt), isChanged);
                    } else {
                        dfsh(cnt + 1, total + 2 * sum, flag | (1 << cnt), isChanged);
                    }
                } else {
                    for (int r = 0; r < n; r++) {
                        if (r == 0 || r == n - 1) {
                            sum += arrayA[r][idx];
                        } else {
                            sum += 2 * arrayA[r][idx];
                        }
                    }

                    if (cnt == 0 || cnt == m - 1) {
                        dfsh(cnt + 1, total + sum, flag | (1 << idx), isChanged);
                    } else {
                        dfsh(cnt + 1, total + 2 * sum, flag | (1 << idx), isChanged);
                    }
                }
                return;
            }

            for (int r = 0; r < n; r++) {
                if (r == 0 || r == n - 1) {
                    sum += arrayA[r][c];
                } else {
                    sum += 2 * arrayA[r][c];
                }
            }

            idx = cnt != c ? cnt : -1;

            if (cnt == 0 || cnt == m - 1) {
                dfsh(cnt + 1, total + sum, flag | (1 << c), cnt != c ? false : true);
            } else {
                dfsh(cnt + 1, total + 2 * sum, flag | (1 << c), cnt != c ? false : true);
            }

        }

    }

    private static void dfsv(int cnt, int total, int flag, boolean isChanged) {

        if( cnt == n ) {
//            System.out.println("dfsv : " + total);
            max = Math.max(max,total);
            return;
        }

        for( int r = 0; r < n; r++ ) {

            if( ( flag & (1<<r) ) != 0 ) continue;

            int sum = 0;

            // 이미 교환했다면 해당 열에 맞지않으면
            if( !isChanged ) {
                if ( (flag & (1<<cnt)) == 0) {
                    for (int c = 0; c < m; c++) {
                        if (c == 0 || c == m - 1) {
                            sum += arrayA[cnt][c];
                        } else {
                            sum += 2 * arrayA[cnt][c];
                        }
                    }

                    if (cnt == 0 || cnt == n - 1) {
                        dfsv(cnt + 1, total + sum, flag | (1 << cnt), isChanged);
                    } else {
                        dfsv(cnt + 1, total + 2 * sum, flag | (1 << cnt), isChanged);
                    }
                } else {
                    for (int c = 0; c < m; c++) {
                        if (c == 0 || c == m - 1) {
                            sum += arrayA[idx][c];
                        } else {
                            sum += 2 * arrayA[idx][c];
                        }
                    }

                    if (cnt == 0 || cnt == n - 1) {
                        dfsv(cnt + 1, total + sum, flag | (1 << idx), isChanged);
                    } else {
                        dfsv(cnt + 1, total + 2 * sum, flag | (1 << idx), isChanged);
                    }
                }
                return;
            }

            for( int c = 0; c < m; c++ ) {
                if( c == 0 || c == m - 1) {
                    sum += arrayA[r][c];
                } else {
                    sum += 2*arrayA[r][c];
                }
            }

            idx = cnt != r ? cnt : -1;

            if( cnt == 0 || cnt == n - 1) {
                dfsv(cnt + 1, total + sum, flag | (1<<r), cnt != r ? false : true);
            } else {
                dfsv(cnt + 1, total + 2*sum,flag | (1<<r) , cnt != r ? false : true);
            }

        }

    }

    private static void printArr(int[][] array){
        System.out.println("==== 배열 출력 =====");
        for( int r = 0; r < n; r++ ) {
            System.out.println(Arrays.toString(array[r]));
        }
    }
}

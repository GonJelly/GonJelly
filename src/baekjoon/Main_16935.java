package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16935 {

    private static int N,M,R;
    private static String[][] target;
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/arrayCycle3_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());           //
        M = Integer.parseInt(st.nextToken());           //
        R = Integer.parseInt(st.nextToken());           // 연산횟수

        target = new String[N][M];
        for(int i = 0; i < N; i ++) {
            target[i] = in.readLine().split(" ");
        }
        st = new StringTokenizer(in.readLine());
        String[] operators = new String[R];
        for( int i = 0; i < R; i++) {
            operators[i] = st.nextToken();
        }
        System.out.println(Arrays.toString(operators));
        for(String mode : operators) {
            choose(mode);
        }
        for(String[] s : target) {
            for(int i = 0; i < s.length; i++) {
                sb.append(s[i] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void choose(String mode) {
        switch (mode) {
            case "1":
                updown();
                break;
            case "2":
                rightleft();
                break;
            case "3":
                right();
                break;
            case "4":
                left();
                break;
            case "5":
                fiveSquare();
                break;
            case "6":
                sixSquare();
                break;
        }
    }

    private static void updown() {

//        Queue<String[]> front = new LinkedList<>();
//        Queue<String[]> back = new LinkedList<>();
//
//        for(int i = 0; i < N/2; i++) {front.offer(target[i]);}
//        for(int i = N-1; i >= N/2; i--) { back.offer(target[i]);}
//
//        for(int i = 0; i < N/2; i++) {target[i] = back.poll();}
//        for(int i = N-1; i >= N/2; i--) {target[i] = front.poll();}
        for(int i = 0; i < target.length/2; i++) {
            for(int j = 0; j < target[0].length; j++) {
                String temp = target[i][j];
                System.out.print(target[0].length - i - 1);
                target[i][j] = target[target.length - i - 1][j];
                target[target.length - i - 1][j] = temp;
            }
        }
        getArray(target);
    }

    private static void rightleft() {
        int n = target.length;
        int m = target[0].length;
        String[][] temp = new String[n][m];
        for(int i = 0; i < m/2; i++) {
            for(int j = 0; j < n; j++) {
                temp[j][m-i-1] = target[j][i];
            }
        }
        for(int i = m-1, count = 0; i >= m/2; i--) {
            for(int j = 0; j < n; j++) {
                temp[j][count] = target[j][i];
            }
            count++;
        }
        target = temp;
//        for(int i = 0; i < target[0].length/2; i++) {
//            for(int j = 0; j < target.length; j++) {
//                String temp = target[j][i];
//                System.out.print(target[0].length - i - 1);
//                target[j][i] = target[j][target[0].length - i - 1];
//                target[j][target[0].length - i - 1] = temp;
//            }
//        }
        getArray(target);
    }

   private static void right() {
       int row = target.length;
       int col = target[0].length;
        String[][] temp = new String[col][row];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                temp[j][row-i-1] = target[i][j];
            }
        }
        target = temp;
   }

   private static void left() {
        int row = target.length;
        int col = target[0].length;
       String[][] temp = new String[col][row];
       for(int i = 0; i < row; i++) {
           for(int j = 0; j < col; j++) {
               temp[col-j-1][i] = target[i][j];
           }
       }
       getArray(temp);
       target = temp;
   }

   private static void fiveSquare(){

       int n = target.length/2;
       int m = target[0].length/2;

        String[][] temp = new String[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                temp[i][j] = target[i][j];
            }
        }
       // 4번 --> 1번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j] = target[i+n][j];
            }
        }
       getArray(target);
       // 3번 --> 4번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j] = target[i+n][j+m];
            }
        }
       getArray(target);
       // 2번 --> 3번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j+m] = target[i][j+m];
            }
        }
       getArray(target);
        // 1번 --> 2번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j+m] = temp[i][j];
            }
        }
       getArray(target);
   }

    private static void sixSquare(){

        int n = target.length/2;
        int m = target[0].length/2;

        String[][] temp = new String[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                temp[i][j] = target[i][j];
            }
        }

        // 1번 <-- 2번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j] = target[i][j+m];
            }
        }
        getArray(target);
        // 2번 <-- 3번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j+m] = target[i+n][j+m];
            }
        }
        getArray(target);
        // 3번 <-- 4번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j+m] = target[i+n][j];
            }
        }
        getArray(target);
        // 4번 <-- 1번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j] = temp[i][j];
            }
        }
        getArray(target);
    }

    private static <T> void getArray(T[][] temp){
        System.out.println("--------------------------------");
        for(T[] tm : temp) {
            System.out.println(Arrays.toString(tm));
        }
        System.out.println("--------------------------------");
    }
}

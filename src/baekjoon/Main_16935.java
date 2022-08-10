package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16935 {

    private static int N,M,R;
    private static String[][] target;
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./res/baekjoon/arrayCycle3_input.txt");
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

        String[] operators = in.readLine().split(" ");
        for(String mode : operators) {
            target = choose(mode);
        }
        for(String[] s : target) {
            for(int i = 0; i < s.length; i++) {
                sb.append(s[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static String[][] choose(String mode) {
        switch (mode) {
            case "1":
                return updown();
            case "2":
                return rightleft();
            case "3":
                return right();
            case "4":
                return left();
            case "5":
                return fiveSquare();
            case "6":
                return sixSquare();
            default:
                return null;
        }
    }

    private static String[][] updown() {

        Queue<String[]> front = new LinkedList<>();
        Queue<String[]> back = new LinkedList<>();

        for(int i = 0; i < N/2; i++) {front.offer(target[i]);}
        for(int i = N-1; i >= N/2; i--) { back.offer(target[i]);}

        for(int i = 0; i < N/2; i++) {target[i] = back.poll();}
        for(int i = N-1; i >= N/2; i--) {target[i] = front.poll();}

        return target;
    }

    private static String[][] rightleft() {
        String[][] temp = new String[N][M];
        for(int i = 0; i < M/2; i++) {
            for(int j = 0; j < N; j++) {
                temp[j][M-i-1] = target[j][i];
            }
        }
        for(int i = M-1, count = 0; i >= M/2; i--) {
            for(int j = 0; j < N; j++) {
                temp[j][count] = target[j][i];
            }
            count++;
        }
        return temp;
    }

   private static String[][] right() {
       int row = target.length;
       int col = target[0].length;
        String[][] temp = new String[col][row];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                temp[j][row-i-1] = target[i][j];
            }
        }
        return temp;
   }

   private static String[][] left() {
        int row = target.length;
        int col = target[0].length;
       String[][] temp = new String[col][row];
       for(int i = 0; i < row; i++) {
           for(int j = 0; j < col; j++) {
               temp[col-j-1][i] = target[i][j];
           }
       }
       return temp;
   }

   private static String[][] fiveSquare(){

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
       // 3번 --> 4번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j] = target[i+n][j+m];
            }
        }
       // 2번 --> 3번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j+m] = target[i][j+m];
            }
        }
        // 1번 --> 2번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j+m] = temp[i][j];
            }
        }
        return target;
   }

    private static String[][] sixSquare(){

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
        // 2번 <-- 3번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i][j+m] = target[i+n][j+m];
            }
        }
        // 3번 <-- 4번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j+m] = target[i+n][j];
            }
        }
        // 4번 <-- 1번
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                target[i+n][j] = temp[i][j];
            }
        }

        return target;
    }
}

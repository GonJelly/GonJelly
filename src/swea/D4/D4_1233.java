package swea.D4;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class D4_1233 {

    private static String[][] tree;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/operatorCk_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 10;

        for( int t = 1; t <= T; t++) {
            int out = 1;
            int nodes = Integer.parseInt(in.readLine());
            tree = new String[nodes+1][];

            for(int i = 1; i <= nodes; i++) {
                tree[i] = in.readLine().split(" ");
//                System.out.println(Arrays.toString(tree[i]));
            }

            for( int j = 1; j <= nodes; j++) {
                if( isCheck(tree[j][1]) && tree[j].length != 4) {
                    out = 0;
                    break;
                } else if ( !isCheck(tree[j][1]) && tree[j].length != 2){
                    out = 0;
                    break;
                }
            }

            sb.append("#").append(t).append(" ").append(out).append("\n");
        }

        System.out.println(sb);
        in.close();
    }

    private static boolean isCheck(String syn) {
        switch ( syn ) {
            case "-":
                return true;
            case "+":
                return true;
            case "/":
                return true;
            case "*":
                return true;
            default :
                return false;
        }
    }
}

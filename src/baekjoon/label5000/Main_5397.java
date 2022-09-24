package baekjoon.label5000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/* 키로거 */
public class Main_5397 {

    private static final char BACKSPACE = '-';
    private static final char RIGHT     = '>';
    private static final char LEFT      = '<';

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/keylogger_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++) {

            Stack<Character> stack1 = new Stack<>();
            Stack<Character> stack2 = new Stack<>();

            char[] input = in.readLine().toCharArray();

            for(char l : input) {

                if( l == BACKSPACE ) {
                    if( !stack1.isEmpty() ) {
                        stack1.pop();
                    }
                    continue;
                }

                if ( l == RIGHT ) {
                    if( !stack2.isEmpty() ) {
                        stack1.push(stack2.pop());
                    }
                    continue;
                }

                if ( l == LEFT ) {
                    if( !stack1.isEmpty() ) {
                        stack2.push(stack1.pop());
                    }
                    continue;
                }

                stack1.push(l);
            }
//            int len1 = stack1.size();
//            for(int i = 0; i < len1; i++) {
//                stack2.push(stack1.pop());
//            }
//            int len2 = stack2.size();
//            for(int i = 0; i < len2; i++) {
//                sb.append(stack2.pop());
//            }

            while ( !stack1.isEmpty() ) {
                stack2.push(stack1.pop());
            }

            while ( !stack2.isEmpty() ) {
                sb.append(stack2.pop());
            }
            sb.append("\n");
        }
        System.out.print(sb);
        in.close();
        long end = System.currentTimeMillis();
        System.out.println(start + " : " + end);
        long time = (end - start);
        System.out.println( time + "ms");
    }
}

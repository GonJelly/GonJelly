package swea;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class D4_1218 {

    static String open = "<[{(";
    static String close = ">]})";

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/syntex_input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int t = 1; t <= 10; t++) {

            Stack<Character> stack1 = new Stack<>();
            int len = Integer.parseInt(br.readLine());
            char[] chars = br.readLine().toCharArray();
            boolean ischeck = true;

            for(int i = 0; i < len; i++) {

                if( open.indexOf(String.valueOf(chars[i])) > -1 ) {
                    stack1.push(chars[i]);
                }

                if( close.indexOf(String.valueOf(chars[i])) > -1 ) {
                    if ( !isCheck(stack1.pop(), chars[i] ) ) {
                        ischeck = false;
                        break;
                    }
                }
            }

            if( ischeck ) {
                System.out.printf("#%d 1%n",t);
            } else {
                System.out.printf("#%d 0%n",t);
            }
        }


    }

    private static boolean isCheck(char open , char close) {
        if( open == '<' & close == '>' ) {
            return true;
        } else if( open == '[' & close == ']' ) {
            return true;
        } else if( open == '{' & close == '}' ) {
           return true;
        } else if( open == '(' & close == ')' ) {
            return true;
        } else {
            return false;
        }
    }
}

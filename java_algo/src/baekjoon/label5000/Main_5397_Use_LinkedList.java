package baekjoon.label5000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 키로거 */
public class Main_5397_Use_LinkedList {

    private static final char BACKSPACE = '-';
    private static final char RIGHT     = '>';
    private static final char LEFT      = '<';

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/keylogger_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++) {

            List<Character> out = new LinkedList<>();
            char[] input = in.readLine().toCharArray();
            int curser = 0;

            for(char l : input) {
//                System.out.print(l + " => ");
                if( l == BACKSPACE && curser != 0) {
//                    System.out.println("백스페이스 : " + l);
                    --curser;
                    out.remove(curser);
//                    System.out.println(" " + curser);
                    continue;
                } else if ( l == LEFT ) {
//                    System.out.print("좌로 이동 : " + l);
                    --curser;
                    curser = curser <= 0 ? 0 : curser;
//                    System.out.println(" " + curser);
                    continue;
                } else if( l == RIGHT ) {
//                    System.out.print("우로 이동 : " + l);
                    curser = curser < out.size() ? ++curser : out.size() ;
//                    System.out.println(" " + curser);
                    continue;
                }
                out.add(curser++,l);
//                System.out.println("입력 :" + l + " 커서 값 : " + curser);
            }

            for(char c : out) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/* 키로거 */
public class Main_5397 {

    private static final String BACKSPACE = "-";
    private static final String RIGHT = ">";
    private static final String LEFT = "<";

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            List<String> input = new LinkedList<>();
            int curser = 0;
            while ( st.hasMoreTokens() ) {
                String key = st.nextToken();
                if( key.equals(BACKSPACE) ) {

                } else if( key.equals(RIGHT) ) {

                } else if( key.equals(LEFT) ) {

                }
            }

        }
    }
}

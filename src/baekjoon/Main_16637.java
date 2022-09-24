package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_16637 {

    static int n, oper;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        // 연산식의 길이 초기화
        n = Integer.parseInt(st.nextToken());
        // 연산식을 어떻게 저장할 것인가? list? queue? static
        List<String> expression = new ArrayList<>();
        Stack<String> express = new Stack<String>();
        String[] tmp = in.readLine().split("");

        for( int x = 0; x < n; x++){
            express.push(tmp[x]);
            if ( tmp[x].equals("+") || tmp[x].equals("-") ) {
                oper++;
            }
        }


    }

    public static void dfs(int cnt,int dept, Stack<String> expression) {

        // 괄호의 갯수를 충족했다면 종료
        if( cnt == n ) {
            // 괄호를 1개 이상 사용하여야한다.
            if( dept > 0 ) {

            }
            return;
        }


    }
}

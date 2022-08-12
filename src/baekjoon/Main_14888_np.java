package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 시간 제한 : 2초 메모리 : 128MB
* N : 숫자의 갯수 1 <= N <= 11
* Ai : 숫자의 범위 1 <= Ai <= 100
* */
public class Main_14888_np {

    private static int n, max, min;
    private static LinkedList<String> operand;
    private static List<String> operators;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/operator_insert_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long start = System.nanoTime();

        n = Integer.parseInt(in.readLine());            // 숫자의 갯수
        // n개의 숫자 할당
        operand = new LinkedList<>();
        operators = new ArrayList<>();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            operand.add(i,st.nextToken());
        }
        // 사용가능한 연산자 갯수 할당
        st = new StringTokenizer(in.readLine());
        getOperator("+",Integer.parseInt(st.nextToken()));
        getOperator("-",Integer.parseInt(st.nextToken()));
        getOperator("*",Integer.parseInt(st.nextToken()));
        getOperator("/",Integer.parseInt(st.nextToken()));

//        System.out.println(operators);
//        System.out.println(operand);
        List<char[]> temp = new ArrayList<>();        // 연산식을 저장할 공간
        expression();

        sb.append(max).append("\n").append(min);
        System.out.println(sb);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000000 +"s");
        in.close();
    }

    private static void expression() {

        char[] temp = new char[operand.size()];
        for( int i = 0; i < operand.size(); i++) {
            temp[i] = operand.get(i).charAt(0);
        }

        int i = operand.size() - 1;
        while ( temp[i] > temp[i-1]) --i;

        int j = operand.size() - 1;
        while ( temp[j] > temp[j-1]) --j;


    }

    private static String getOperater(int op1 , int op2, String operator) {
        switch ( operator ) {
            case "+":
                return String.valueOf(op1 + op2);
            case "-":
                return String.valueOf(op1 - op2);
            case "*":
                return String.valueOf(op1 * op2);
            case "/":
                int result = 0;
                if( op1 < 0) {
                    op1 = -op1;
                    result = -(op1 / op2);
                    return String.valueOf(result);
                }
                result = (op1 / op2);
                return String.valueOf(result);
            default:
                return null;
        }
    }

    private static void getOperator(String order, int count) {
        for(int i = 1; i <= count; i++) {
            operators.add(order);
        }
    }
}

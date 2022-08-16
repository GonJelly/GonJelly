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
* 
* 메모리와 시간복잡도를 다시 생각해보고 다르게 해보겠음
* */
public class Main_14888 {

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
        String[] temp = new String[n-1];        // 연산식을 저장할 공간
        expression(temp,0,0);

        sb.append(max).append("\n").append(min);
        System.out.println(sb);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000000 +"s");
        in.close();

    }

    private static void expression(String[] temp,int cnt,int flag) {

        if( cnt == n - 1 ) {
//            System.out.println(Arrays.toString(temp));
            String result;
            Queue<String> q = new LinkedList<>();
            for(int i = 0; i < n - 1;i++ ) {
                q.offer(operand.get(i));
                q.offer(temp[i]);
            }
            q.offer(operand.get(n-1));
            result = q.poll();
            while ( !q.isEmpty() ) {
                String oper = q.poll();
                int op2 = Integer.parseInt(q.poll());
                result = getOperater(Integer.parseInt(result),op2,oper);
            }
            int tmp = Integer.parseInt(result);
            if( max < tmp) max = tmp;
            if( min > tmp) min = tmp;
            return;
        }

        for(int i = 0; i < n - 1; i++) {
            if( (flag & 1<<i) != 0) continue;
            temp[cnt] = operators.get(i);
            /*System.out.println(temp);*/
            expression(temp,cnt + 1,flag | 1<<i);
        }
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

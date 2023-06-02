package baekjoon.label1000;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1026 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[] A = getArray(new StringTokenizer(br.readLine()), N);
        int[] B = getArray(new StringTokenizer(br.readLine()), N);

        Arrays.sort(A); // A 올림차순으로 재정렬

        Stack<Integer> stack = getindex(B);

//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(B));

        for(int i = 0; i < N; i++ ){
            sum += (A[i] * stack.pop());
        }

        System.out.println(sum);
    }

    private static int[] getArray(StringTokenizer st,int n) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static Stack<Integer> getindex(int[] B) {
        Stack<Integer> stack = new Stack<>();
        Arrays.stream(B).sorted().forEach(
                s -> stack.push(s)
        );
        return stack;
    }

}

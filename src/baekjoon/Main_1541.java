package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1541 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        int total = 0;                                      // 합산을 저장하는 곳
        int min = Integer.MAX_VALUE;                        // 최솟값 저장
        String[] operater = in.readLine().split("-"); // +로 묶기위해서 - 기준으로 split
        for (int i = 0; i < operater.length; i++) {
            StringTokenizer st = new StringTokenizer(operater[i], "+");
            int sum = 0;
            while (st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken().trim());
            }
            operater[i] = String.valueOf(sum);
        }
        total = Integer.parseInt(operater[0]);
        for (int j = 1; j < operater.length; j++) {
            total -= Integer.parseInt(operater[j]);
        }

        min = Math.min(min, total);                 // 최솟값 비교;
        System.out.println(min);;

    }
}

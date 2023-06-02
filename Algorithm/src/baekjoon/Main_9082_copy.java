package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9082_copy {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T-- != 0) {
            int N = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String mine = br.readLine();

            int startIndex = 1, sum = 0;
            if(line.length() % 3 == 1) { // 홀수
                startIndex = 0;
            }

            for(int i = startIndex; i < line.length(); i+=3) {
                sum += line.charAt(i) - '0';
            }

            System.out.println(sum);
        }

    }
}

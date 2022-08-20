package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 : 2초 메모리 : 256MB
public class D4_3234 {

    private static int n, count;
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/swea/input_3234_D4.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());            // 테케 횟수

        for(int t = 1; t <= T; t++) {

            n = Integer.parseInt(in.readLine());        // 무게추의 갯수
            count = 0;                                  // 경우의 수 초기화
            int[] chu = new int[n];                     // 무게의 추

            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                chu[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(chu);
            // 추의 올리는 순서를 다르게해서 연산
            // 오른쪽에 올리는 경우 왼쪽에 올리는 경우를 분기로 나눠서 연산
            do {
                juwol(0,0,0,chu);
            } while ( np( chu ));

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + "ms");

    }
    private static void juwol(int cnt ,int left, int right,int[] chu) {
        // 오른쪽에 있는 무게가 커지면 종료
        if( left < right) {
            return;
        }
        // 모든 추를 사용하면 왼쪽 , 오른쪽 무게 확인
        if (cnt == n ) {
            count++;
//            System.out.println(left +"  :::  "+ right);
            return;
        }
        if ( left >= right + chu[cnt] ) {
            juwol(cnt + 1, left, right + chu[cnt], chu);
        }
        juwol(cnt + 1, left + chu[cnt], right, chu);
    }

    private static boolean np (int[] chu) {

        int i = n - 1;
        while (i > 0 && chu[i-1] >= chu[i] ) --i;

        if( i == 0) return false;

        int j = n - 1;
        while (chu[i-1] >= chu[j] ) --j;

        swap(chu , i - 1, j);

        int k = n - 1;
        while ( i < k ) swap(chu,i++,k--);

        return true;
    }

    private static void swap(int[] chu, int i, int j) {

        int temp = chu[i];
        chu[i] = chu[j];
        chu[j] = temp;

    }

}

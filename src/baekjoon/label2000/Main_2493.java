package baekjoon.label2000;

import java.io.*;
import java.util.*;

public class Main_2493 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/tower_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> compare = new Stack<>();             // 비교할 타워가 저장될 공간
        Stack<Integer> idx = new Stack<>();                 // 타워의 인덱스를 저장할 공간
        StringBuilder sb = new StringBuilder();             // System.out.print로 발생하는 시간문제를 해결하기 위해서 추가

        int N = Integer.parseInt(in.readLine());    // 탑의 수!
        int[] tower = new int[N+1];                 // 탑의 높이를 저장할 배열 ( 1번부터 라서 N+1)
        int[] point = new int[N+1];         // 타워가 왼쪽으로 레이저를 발사해서 수신한 타워의 index를 저장할 공간
        // 타워의 높이를 입력하여 tower에 저장 start
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }
        // 타워의 높이를 입력하여 tower에 저장 end

        // 1타워 옆으로는 아무것도 없으니까 먼저 stack에 저장
        compare.push(tower[1]);
        idx.push(1);
        // 1타워 옆으로는 아무것도 없기 때문에 무시함
        for(int j = 2; j <= N; j++) {
            while ( !compare.isEmpty() ) {
                // 만약 해당 타워의 높이보다 작거나 같으면 비교할 필요가 없으니까 제거
                // 해당 타워의 높이보다 높으면 해당 위치를 point에 저장하고 compare에 추가
                if( compare.peek() <= tower[j] ) {
                    compare.pop();    // 타워 제거
                    idx.pop();        // 타워 index 제거
                } else {
                    point[j] = idx.peek();      // 수신 타워 위치 저장
                    compare.push(tower[j]);     // 비교대상 타워에 추가
                    idx.push(j);               // 비교대상 타워 위치 저장
                    break;
                }
            }
            // 비교할 타워가 없다는 것은 제일 큰 타월는 것이니까
            // compare, idx 저장하고 다음 타워로 이동
            if( compare.isEmpty() ) {
                compare.push(tower[j]);
                idx.push(j);
            }
        }

        // 1부터 마지막까지 비교가 끝났으면 출력
        for(int i = 1; i <= N; i++) {
            sb.append(point[i]).append(" ");
        }
        System.out.println(sb);
        in.close();
    }
}
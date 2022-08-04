package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2164 {
    /*첫째 줄에 정수 N(1 ≤ N ≤ 500,000)이 주어진다.*/
    /*카드가 한 장 남을 때까지 반복*/
    public static void main(String[] args) throws IOException {

        // 입력하는 부분
        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/card_input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력하는 부분
        FileOutputStream fos = new FileOutputStream("./study_algorithm/output/beakjoon/card_output.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(bos));
        // x값을 받아오는 부분
        int x = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        // 출력값을 저장할 Stringbuilder
        StringBuilder sb = new StringBuilder();
        // 큐에 자료 삽입하기
        for(int i = 1; i <= x; i++) {
            que.offer(i);
        }
        // 큐의 값 확인하기
//        System.out.println(que);
        /*카드가 한 장 남을 때까지 반복*/
        while( true ) {
            // 만약 입력반은 카드가 1개라면 프로세스를 종료
            if( que.size() == 1) {
                sb.append(que.poll());
                break;
            }
            // 큐 제일 위에 있는 값을 저장할 변수
            int temp = 0;
            /*제일 위에 있는 카드를 바닥에 버린다.*/
            que.poll();
            // 카드가 1개라면 프로세스를 종료
            if( que.size() == 1) {
                sb.append(que.poll());
                break;
            }

            /*제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.*/
            temp = que.poll();
            que.offer(temp);
        }
        // 카드 출력
        System.out.println(sb);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

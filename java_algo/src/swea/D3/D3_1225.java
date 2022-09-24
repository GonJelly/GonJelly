package swea.D3;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D3_1225 {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/input_1225.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream out = new FileOutputStream("./study_algorithm/output/swea/output_1225.txt");
        BufferedOutputStream bos = new BufferedOutputStream(out);
        StringBuilder output_sb = new StringBuilder();

        for( int test_case = 1; test_case <= 10; test_case++) {
            int t = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> que = new LinkedList<>();                // 암호 저장 공간
            // 암호 입력
            while (st.hasMoreTokens()) {
                que.offer(Integer.parseInt(st.nextToken()));
            }
            // 마지막 숫자가 0일 나올 때까지 반복
            while ( true ) {
                int temp = 0;
                // 1사이클 == 5회 1~5 증감값 늘려가면서 암호화
                for( int i = 1; i <= 5; i++) {
                    temp = que.poll() - i;
                    // 마지막이 0이 되면 사이클 '종료' 아니면 감소시키고 마지막으로 입력
                    if (temp <= 0) {
                        temp = 0;
                        que.offer(0);
                        break;
                    } else {
                        que.offer(temp);
                    }
                }
                // 암호가 마지막이 0이면 암호생성 종료
                if( temp == 0 ) break;
            }
            // 생성한 암호 출력
            System.out.print("#" + t + " ");
            while (!que.isEmpty()) {
                sb.append(que.poll()).append(" ");
            }
            System.out.print(sb.append("\n"));  // console에 출력할 String
            output_sb.append(sb);               // 외부파일로 출력할 String
        }
        bos.write(output_sb.toString().getBytes());
        bos.flush();
        bos.close();
        br.close();
    }
}
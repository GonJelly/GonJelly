package swea;

import java.io.*;
import java.util.*;

public class D3_1228 {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/encoding_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./study_algorithm/output/swea/encoding_output.txt"));
        StringBuilder sb = new StringBuilder();
        int T = 10;

        for(int t = 1; t <= T; t++) {

            int encoding_length = Integer.parseInt(in.readLine());      // 암호문의 길이
            int command_count = 0;                                      // 명령어의 갯수
            List<Integer> encoding = new LinkedList<>();                // 암호문을 저장할 장소

            StringTokenizer st = new StringTokenizer(in.readLine());    // 암호문 삽입
            while( st.hasMoreTokens() ) {
                encoding.add(Integer.parseInt(st.nextToken()));
            }

            command_count = Integer.parseInt(in.readLine());            // 염령어의 갯수 입력
            // 특수한 처리기를 이용한 암호문 수정 start
            st = new StringTokenizer(in.readLine(),"I");          // I(삽입) 문자를 기준으로 나눔
            for( int i = 0; i < command_count; i++) {
                String[] temp = st.nextToken().trim().split(" ");
                int x = Integer.parseInt(temp[0]);                      // x값 삽입 ( 추가 숫자가 들어갈 장소 )
                int y = Integer.parseInt(temp[1]);                      // y값 삽입 ( 추가 숫자의 갯수 )
                for(int j = 2; j < y + 2; j++) {
                    encoding.add(x++,Integer.parseInt(temp[j]));
                }
            }
            // 특수한 처리기를 이용한 암호문 수정 end
            sb.append("#"+t);
            for(int i = 0; i < 10; i++) {
                sb.append(" ").append(encoding.get(i));
            }
            sb.append("\n");
        }

        System.out.println(sb);
        bos.write(sb.toString().getBytes());
        bos.flush();
        bos.close();
        in.close();
    }
}

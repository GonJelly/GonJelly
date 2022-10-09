package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class SW_5658 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 횟수

        for (int t = 1; t <= T; t++ ) {

            st = new StringTokenizer(in.readLine()); // 숫자의 갯수(N) 및 10진수로 바굴 숫자 K번째

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            // 중복을 없애기 위해서 set에 저장
            Set<String>  total = new HashSet<>();

            Queue<String> lock = new ArrayDeque<>();
            // 비밀번호 할당
            String[] array = in.readLine().split("");
            for( String password : array ) {
                lock.offer(password);
            }

            int cycle = N/4; // 회전 횟수

            for( int i = 0; i < cycle; i++ ) {
                // 적힌 숫자를 나누기 위해서 String 으로 변환
                String tmp = stringToQue(lock);
                // 각 변에 적힌 숫자를 저장
                for( int range = 0; range < N; range += cycle ) {
                    String sub = tmp.substring(range, range + cycle);
                    total.add(sub);
                }
                // 시계방향으로 회전
                String first = lock.poll();
                lock.offer(first);

            }

            String[] result = new String[total.size()];
            int idx = 0;
            Iterator<String> iterator = total.iterator();
            while ( iterator.hasNext() ) {
                result[idx++] = iterator.next();
            }
            // 내림차순으로 정렬
            Arrays.sort(result, Comparator.reverseOrder());

            sb.append("#").append(t).append(" ").append(Integer.parseUnsignedInt(result[K-1],16)).append("\n");
        }
        System.out.println(sb);
    }

    private static String stringToQue(Queue<String> queue) {

        StringBuilder copy = new StringBuilder();

        while ( copy.length() != queue.size() ) {

            copy.append(queue.peek());
            String tmp = queue.poll();
            queue.offer(tmp);

        }

        return copy.toString();
    }

}

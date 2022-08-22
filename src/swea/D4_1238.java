package swea;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Contact
 * @author 박준영
 */
public class D4_1238 {

    private static int[] max;

    public static void main(String[] args) throws IOException {

        File file = new File("./study_algorithm/output/swea/contact.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream out = new FileOutputStream(file);
        StringBuilder sb = new StringBuilder();
        int T = 10;

        for(int t = 1; t <= T; t++) {
            // 연락망의 갯수 및 시작 Node 받아오기
            StringTokenizer st = new StringTokenizer(in.readLine());
            // 최대 깊이와 최대 노드의 번호를 저장하는 공간
            max = new int[] {0,0};
            // 노드의 수!!
            int n = Integer.parseInt(st.nextToken());
            // 처음 시작 정점
            int root = Integer.parseInt(st.nextToken());
            // 순환을 막기위한 방문체크 불린배열
            boolean[] visit = new boolean[n];

            // 인접행렬
            int[][] list = new int[101][101];

            // From / to 쌍으로 node 정보를 받아와서 인접행렬 작성
            st = new StringTokenizer(in.readLine());
            while( st.hasMoreTokens() ) {
                // 시작 정점
                int from = Integer.parseInt(st.nextToken());
                // 다음 정점
                int to = Integer.parseInt(st.nextToken());
                // 단방향으로 연결되어 있으면 1로 표시
                list[from][to] = 1;
            }

            // 깊이 탐색
            bfs(root, list, visit);
            // TC 결과 값을 저장
            sb.append("#" + t + " ").append(max[0]).append("\n");
        }
        // 일괄 출력
        System.out.println(sb);
        out.write(sb.toString().getBytes());
        out.flush();
        out.close();
        in.close();
    }


    private static void bfs(int start, int[][] list, boolean[] visit) {

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {start, 0});
        visit[start] = true;    // 방문체크

        while ( !que.isEmpty() ) {

            int[] node = que.poll();
            // 깊이가 깊어지면 최대 초기화
            if( node[1] > max[1] ) max = node;
            // 깊이가 같으면 정점의 값을 비교
            if( node[1] == max[1] )
                if( node[0] > max[0] )
                    max = node;
//            System.out.println(node + " ");

            for(int r = 0; r < list.length; r++) {
                // 연락이 가능하고 | 연락을 받은 적이 있는지 확인( 연락을 받은 적이 없어야함 )
                if ( list[node[0]][r] != 0 && !visit[r] ) {
                    visit[r] = true;
                    que.offer(new int[] {r,node[1] + 1});
                }
            }
        }
    }
}

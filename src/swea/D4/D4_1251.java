package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제제목 : 하나로
 * @author 박준영
 *
 */
public class D4_1251 {

    private static int[] node;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t<= T; t++) {
            double total = 0;
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());   // 섬의 수
//            double[][] dis = new double[n][n];
            st = new StringTokenizer(in.readLine());
            int[] row = new int[n];                     // x축
            for(int x = 0; x < n; x++) {
                row[x] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            int[] col = new int[n];                     // y축
            for(int y = 0; y < n; y++) {
                col[y] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            double e = Double.parseDouble(st.nextToken());   // 세율

            node = new int[n];
            for(int i = 0; i < n; i++) {
                make(i);
            }

            Queue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
                @Override
                public int compare(double[] o1, double[] o2) {
                    if( o1[2] - o2[2] > 0 ) return 1;
                    else if( o1[2] - o2[2] < 0 ) return -1;
                    else {
                        if (o1[1] - o2[1] > 0) return 1;
                        else if (o1[1] - o2[1] < 0) return -1;
                        else return 0;
                    }
                }
            });

            for(int r = 0; r < n - 1; r++) {
                for(int c = r + 1; c < n; c++) {
                    double dis = Math.pow(Math.abs(row[r] - row[c]),2) + Math.pow(Math.abs(col[r] - col[c]),2);
                    pq.offer(new double[] { r, c , dis});
                    pq.offer(new double[] { c, r , dis});
                }
            }
//            System.out.println(Arrays.toString(node));
            int count = 0;
            while( !pq.isEmpty() ) {

                double[] ebge = pq.poll();
//                System.out.println(Arrays.toString(ebge));

                int start = (int) ebge[0];
                int end = (int) ebge[1];

                if ( find(start) == find(end) ) continue;

                union(start, end);
//                System.out.println(Arrays.toString(node));
                ++count;
//                System.out.println(ebge[2]);
                total += ebge[2];
//                System.out.println(total);
                if( count == n - 1) break;
            }

            System.out.printf("#%d %.0f%n",t,(e * total));
        }
    }

    private static void make(int x) {
        node[x] = x;
    }

    private static int find(int a) {
        if( a == node[a] ) return a;
        else return node[a] = find(node[a]);
    }

    private static void union(int a, int b) {
        if( find(a) == find(b) ) return;
        node[find(b)] = find(a);
    }
}

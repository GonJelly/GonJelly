package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_5644 {

    static class BC {
        int[] pos;
        int cover;
        int p;

        public BC(int x, int y, int cover, int p) {
            this.pos = new int[]{x,y};
            this.cover = cover;
            this.p = p;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BC{");
            sb.append("pos=").append(Arrays.toString(pos));
            sb.append(", cover=").append(cover);
            sb.append(", p=").append(p);
            sb.append('}');
            return sb.toString();
        }
    }

    private static List<BC> bc;
    private static int[] userA,userB;
    private static List<int[]> score;
    private static boolean[] isSelect;
    private static int[][] moving = {         // 움직이지 않음->0, 상우하좌
            {0,0},{-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./res/swea/BC_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for( int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());       // 이동시간
            int A = Integer.parseInt(st.nextToken());       // 배터리 수
            bc = new ArrayList<>();                         // 배터리정보 저장공간
            userA = new int[] {1,1};                        // 유저A 초기화
            userB = new int[] {10,10};                      // 유저B 초기화

            int[] commandA = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] commandB = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int i = 0; i < A; i++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cover = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bc.add(new BC(x,y,cover,p));
            }



        }
    }
}

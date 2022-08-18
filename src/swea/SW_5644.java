package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

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
    private static PriorityQueue<BC> isSelect;
    private static int[][] moving = {         // 움직이지 않음->0, 상우하좌
            {0,0},{-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/BC_input.txt");
//        FileInputStream file = new FileInputStream("./study_algorithm/res/swea/BC_input_short.txt");
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
            score = new ArrayList<>();                      // 유저의 점수를 저장할 공간
            int[][] map = new int[11][11];
            isSelect = new PriorityQueue<BC>(new Comparator<BC>() {
                @Override
                public int compare(BC o1, BC o2) {
                    if ( o1.p - o2.p > 0) return 1;
                    else if( o1.p - o2.p < 0) return -1;
                    else return 0;
                }
            });

            int[] commandA = new int[M + 1];
            int[] commandB = new int[M + 1];
            commandMaker(in, commandA);
            commandMaker(in, commandB);

            for(int i = 0; i < A; i++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cover = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
                bc.add(new BC(y,x,cover,p));
            }

            map[1][1]  = 2;
            map[10][10]  = 3;

            for(int i = 1; i <= 10; i++) {
                for(int j = 1; j <= 10; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            for(int i = 0; i <= M; i++) {
                getBC(i,commandA[i], commandB[i]);
            }


            int sum = 0;
            for(int i = 0; i < score.size(); i++) {
                sum += score.get(i)[0] + score.get(i)[1];
                System.out.println(Arrays.toString(score.get(i)));
            }
            System.out.println(sum);
        }
    }

    private static void commandMaker(BufferedReader in, int[] command) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < 20; i++ ){
            command[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getBC(int time, int commandA, int commandB) {
        int chargeA = 0;
        int chargeB = 0;

        for(int i = 0; i < bc.size(); i++ ) {
//            System.out.println("=============in===============");
            int diffA = Math.abs(bc.get(i).pos[0] - userA[0]) + Math.abs(bc.get(i).pos[1] - userA[1]);
            int diffB = Math.abs(bc.get(i).pos[0] - userB[0]) + Math.abs(bc.get(i).pos[1] - userB[1]);
            System.out.print(time + "::" + diffA +" ::: "+ diffB);
            System.out.println("유저좌표: " + Arrays.toString(userA) + "::" + Arrays.toString(userB));
            // 유저A와 유저B가 같은 위치에 있는경우;
            if( bc.get(i).cover >= diffA && bc.get(i).cover >= diffB) {
                int size = bc.get(i).p / 2;

                isSelect.offer(bc.get(i));

                if( chargeA < size ) {
                    chargeA = size;
                }

                if( chargeB < size ) {
                    chargeB = size;
                }

                continue;
            }
            if( bc.get(i).cover >= diffA) {
                if( chargeA < bc.get(i).p ) {
                    chargeA = bc.get(i).p;
                }
            }
            if( bc.get(i).cover >= diffB ) {
                if( chargeB < bc.get(i).p ) {
                    chargeB = bc.get(i).p;
                }
            }
        }

        if( isSelect.size() >= 1) {
            System.out.println(isSelect);
            if( chargeA > chargeB) {
                chargeB = isSelect.poll().p;
            } else if (chargeA < chargeB){
                chargeA = isSelect.poll().p;
            } else {
                chargeA = isSelect.poll().p;
                chargeB = isSelect.poll().p;
            }
            isSelect.clear();
        }

        score.add(new int[] {chargeA,chargeB});

        userA[0] += moving[commandA][0];
        userA[1] += moving[commandA][1];

        userB[0] += moving[commandB][0];
        userB[1] += moving[commandB][1];

    }

    private static void ischeck() {

    }


}

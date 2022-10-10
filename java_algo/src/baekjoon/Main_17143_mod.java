package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17143_mod {

    // 격자판의 상어 객체
    static class Shark implements Comparable<Shark>{

        String name;
        int x;  // 행의 위치
        int y;  // 열의 위치
        int s;  // 속도
        int d;  // 방향
        int z;  // 크기

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        // 땅과 제일 가까운 상어를 낚기 위해서 열이 제일 작은 상어가 앞으로 올 수 있도록 처리
        @Override
        public int compareTo(Shark o) {
            return Integer.valueOf(this.x).compareTo(Integer.valueOf(o.x));
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Shark{");
            sb.append("name=").append(name);
            sb.append(", x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", s=").append(s);
            sb.append(", d=").append(d);
            sb.append(", z=").append(z);
            sb.append('}');
            return sb.toString();
        }
    }
    // 상어의 이동방향 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
    static int[][] dir = {
            {},{-1,0},{1,0},{0,1},{0,-1}
    };
    static int R,C,M;
    // Goal : 낚시꾼이 잡은 상어의 총 몸게
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/baekjoon/fisherToShark.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        M = Integer.parseInt(st.nextToken());   // 상어의 수

        // 격자판에 있는 상어를 표시하기 위해서
        List<Shark> list = new ArrayList<>();
        int fisher = 0; // 낚시꾼이 서있는 위치
        int weight = 0; // 낚시꾼이 잡은 상어의 총무게

        for( int cnt = 0; cnt < M; cnt++) {
            st = new StringTokenizer(in.readLine());
            while( st.hasMoreTokens() ) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                Shark shark = new Shark(r,c,s,d,z);
//                shark.name = "상어" + String.valueOf(cnt);
                list.add(shark);
            }
        }

        // 격자판에 상어가 있으면 계속 낚시 or 낚시꾼이 제일 오른쪽에 도달하면 종료
        while( !list.isEmpty() ) {
            // 1칸 이동
            fisher += 1;
            // 상어의 수
            int size = list.size();
            // 제일 오른쪽에 도탁하면 종료
            if( fisher > C ) break;
            // 땅과 제일 가까운 순으로 정렬
            Collections.sort(list);
            // 상어를 낚시 시작
            for( int i = 0; i < size; i++) {

                Shark shark = list.get(i);
                // 낚시꾼이 있는 열에 있는 상어를 낚기
                // ( 땅과 가까운순으로 오름차순되어 있어서 먼저 나온 상어가 잡힐 수 있도록 함 )
                if( shark.y == fisher ) {
                    weight += shark.z;
                    list.remove(shark);
                    size--; // 1마리가 잡혔으니 상어의 수를 줄이기
                    break;
                }
            }

            // 상어가 움직인다.
            for(int i = 0; i < size; i++) {

                Shark shark = list.get(i);
                // 상어의 스피드 만큼 칸을 이동한다.
                for( int speed = 1; speed <= shark.s; speed++) {

                    int nx = shark.x + dir[shark.d][0];
                    int ny = shark.y + dir[shark.d][1];
                    // 격자판을 오버하게 된다면 방향을 반대로 이동
                    if( nx == 0 || nx > R || ny == 0 || ny > C) {
                        // 상어의 방향이 위 or 오른쪽이면 +1
                        if( shark.d == 1 || shark.d == 3 ) shark.d += 1;
                        // 상어의 방향이 위 or 오른쪽이면 -1
                        else shark.d -= 1;
                        // 반대방향으로 이동
                        nx = shark.x + dir[shark.d][0];
                        ny = shark.y + dir[shark.d][1];
                    }

                    shark.x = nx;
                    shark.y = ny;
                }

            }

            Iterator<Shark> iterator = list.iterator();
            Set<Shark> rmList = new HashSet<>();
            while( iterator.hasNext() ) {
                Shark shark = iterator.next();
                for(int i = 0; i < size; i++) {
                    Shark compare = list.get(i);
                    // 같은 상어가 아니고 && 같은 위치에 있다면
                    if( !shark.equals(compare) && shark.x == compare.x && shark.y == compare.y ) {
                        // 무게 비교
                        if( shark.z > compare.z ) {
                            rmList.add(compare);
                        } else if( shark.z < compare.z ) {
                            rmList.add(shark);
                            break;
                        }
                    }
                }
            }
            // 삭제하기
            for( Shark shark : rmList) {
                list.remove(shark);
            }

//            System.out.println("======= 움직인 상어의 결과 =======");
//            list.forEach( s -> System.out.println(s));
        }
        System.out.println(weight);

    }
}

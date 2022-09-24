package baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main_17135 {

    private static int N, M, D, count, max;

    public static void main(String[] args) throws IOException {
        // 입력 값을 받기 위한 Reader 참조변수 inputStream -> Reader (2Byte 씩 입력받음)¡¡
        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/castle_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로의 크기
        M = Integer.parseInt(st.nextToken());   // 가로의 크기
        D = Integer.parseInt(st.nextToken());   // 궁수의 공격 범위(최대거리)

        int[] shooter = new int[M];                  // 궁수 0 ~ M-1 사이에 존재여부 확인
        int[][] board = new int[N+1][M];            // 0~ N-1 적배치 N: 성=궁수배치

        for(int r = 0; r < N; r++) {                // 적 배치 최대시간 : 200
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 맵 확인
//        pringMap(board);

        for(int c = 0 ; c < 3; c++) {
            shooter[M - c - 1] = 1;
        }
        // 궁수 배치 확인
        do {
            count = 0;
//            System.out.println("궁수 배치 하기 start");
            List<Point> enemy = getEnemy(board);
            Collections.sort(enemy, (o1, o2) -> {
                if( o1.y == o2.y ) return (o1.x - o2.x > 0) ? -1 : 1;
                if( o1.y - o2.y > 0) return 1;
                else if( o1.y - o2.y < 0) return -1;
                else return 0;
            });
//            System.out.println(enemy);
            System.out.println(Arrays.toString(shooter));
            game(enemy, shooter);
//            System.out.println(count);
        } while ( next_permutation( shooter ) );

        System.out.println(max);
    }

    private static void game(List<Point> enemy, int[] shooter) {
        // 더이상 적이 없어지면 종료
        if( enemy.size() == 0) {
            System.out.println(count);
            max = Math.max(max, count);
            return;
        }

        enemy.forEach(p -> System.out.print(p + "::"));
        System.out.println();
        // 궁수가 공격한 적의 좌표 저장
        Point[][] attack = new Point[M][1];
        Set<Point> attacked = new HashSet<>();
        // 궁수와 적과의 거리를 측정
        for(int a = 0; a < M; a++) {
            int distance = D;
            if( shooter[a] == 1) {
                for (int b = 0; b < enemy.size(); b++) {
                    int compare = getDistance(a, enemy.get(b));
//                System.out.print(compare + "  ");
                    // 공격 사정범위를 벗어나면 비교하지않음
                    if (compare > D) continue;

                    // 공격대상이 없으면 추가
                    if (attack[a][0] == null) {
                        distance = compare; // 찾는데 3시간.......
                        attack[a][0] = enemy.get(b);
                        continue;
                    }
                    // 거리가 가까우면 바꾸기
                    if (distance > compare) {
                        distance = compare; // 최소거리 저장
                        // 거리가 더 가까우니 바꿔치기
                        attack[a][0] = enemy.get(b);
                    }
                    // 거리는 같은데 왼쪽에 있으면 바꾸기
                    else if (distance == compare) {
                        // 거리가 같은데 왼쪽에 있으면 바꿔치기
                        System.out.println(attack[a][0] + "++++++" + enemy.get(b));
                        if (attack[a][0].y > enemy.get(b).y) {
//                        System.out.println("<---in--->");
                            attack[a][0] = enemy.get(b);
                        }
                    }
                }
            }
        }
//        System.out.println();
        // 공격한 적의 좌표 확인
        for(int i = 0; i < M; i++) {
//            System.out.print(Arrays.toString(attack[i]) + " ::: ");
            if(attack[i][0] != null)
                attacked.add(attack[i][0]);
        }
//        System.out.println();
        count += attacked.size();
        System.out.print(attacked.size() + "<- 공격대상 수  :: 남은 적 -> ");
        // 공격 대상 제거 및 적들 한칸씩 이동
        remove(enemy, attacked);
        System.out.println(enemy.size());
        game(enemy,shooter);
    }

    private static int getDistance(int c, Point enemy) {
        int distance = Math.abs(N - enemy.x) + Math.abs(c - enemy.y);
        return distance;
    }

    private static List<Point> getEnemy(int[][] board) {

        List<Point> enemy = new ArrayList<>();

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                // 성과 가까운 적부터 리스트에 추가
                if( board[N - 1 - r][c] == 1) {
                    enemy.add(new Point(N - 1 - r,c));
                }
            }
        }

        return enemy;
    }

    private static void remove(List<Point> enemy, Set<Point> set) {

        Iterator<Point> rmObj = set.iterator();
        List<Point> tmp = new ArrayList<>();
        while ( rmObj.hasNext() ) {
            tmp.add(rmObj.next());
        }

        // 적 한칸씩 이동
        Iterator<Point> iterator = enemy.listIterator();
        start:
        while( iterator.hasNext() ) {
            Point point = iterator.next();
            // 공격 대상당하면 제거
            for(int i = 0; i < tmp.size(); i++) {
                if ( tmp.get(i).x == point.x && tmp.get(i).y == point.y) {
                    iterator.remove();
                    continue start;
                }
            }
            // 적은 한칸씩 이동
            point.x++;
            // 성의 위치에 도달하면 제외
            if( point.x == N ) {
                iterator.remove();
            }
        }
    }

    private static boolean next_permutation(int[] shooter) {

        int i = M - 1;
        while( i > 0 && shooter[i-1] >= shooter[i]) --i;

        if( i == 0 ) return false;

        int j = M - 1;
        while( shooter[i -1] >= shooter[j]) --j;

        swap(shooter, i - 1, j);

        int k = M - 1;
        while( i < k) swap(shooter, i++, k--);

        return true;
    }
    // 스왑하는 함수
    private static void swap(int[] shooter, int i, int j){
        int temp = shooter[i];
        shooter[i] = shooter[j];
        shooter[j] = temp;
    }

    private static void pringMap(int[][] borad) {
        System.out.println("======= 맵 확인 시작 ========");
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                System.out.printf("%d ",borad[r][c]);
            }
            System.out.println();
        }
        System.out.println("====== 맵 확인 종료 ========");
    }
}
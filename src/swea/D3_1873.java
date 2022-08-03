package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_1873 {

    static int[] tank;            // 탱크의 좌표
    static char[] command;        // 명령어 공간
    static char[][] map;          // 게임 지도
    static int[] dh ={1,0,-1,0};  // 탱크의 x좌표 이동 배열
    static int[] dw ={0,1,0,-1};  // 탱크의 y좌표 이동 배열
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./res/tank_input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){

            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int h = Integer.parseInt(st.nextToken());               // 맵의 세로
            int w = Integer.parseInt(st.nextToken());               // 맵의 가로
            tank = new int[2];                                      // 탱크 좌표 공간 할당


            /* 맵 선언 및 할당 */
            map = new char[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                map[i] = st.nextToken().toCharArray();
            }

            /* 명령어 입력 부분 start */
            st = new StringTokenizer(br.readLine());
            int command_count = Integer.parseInt(st.nextToken());
            command = new char[command_count];

            st = new StringTokenizer(br.readLine());
            command = st.nextToken().toCharArray();
            /* 명령어 입력 부분 end */

            /* 맵 / 명령어 확인 */
//            confirmMap();
//            System.out.println(Arrays.toString(command));

            /* 탱크의 좌표를 찾아내는 작업 */
            findTank();

            /* 탱크가 명령대로 움직이고 움직인 좌표를 재셋팅 */
            for(int k = 0; k < command_count; k++) {
                move(tank[0], tank[1], command[k]);
                findTank();
            }
            
            /* 명령을 모두 수행하고 게임 지도 출력 */
            System.out.printf("#%d ",t);
            confirmMap();
        }
    }
    // 게임 지도 출력하는 메소드
    private static void confirmMap() {
        for (char[] map1 : map) {
            for(char m : map1) {
                System.out.print(m);
            }
            System.out.println();
        }
    }
    // 탱크 좌표 찾는 메소드
    private static void findTank() {
        find:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case '<':
                        tank = new int[]{i, j};
                        break find;
                    case '>':
                        tank = new int[]{i, j};
                        break find;
                    case '^':
                        tank = new int[]{i, j};
                        break find;
                    case 'v':
                        tank = new int[]{i, j};
                        break find;
                }
            }
        }
    }
    // 명령에 따라 탱크 조작하는 메소드
    private static void move(int x, int y, char command) {
        if ( command == 'U' || command == 'D' || command == 'L' || command == 'R') {
            switch ( command ) {
                case 'U':
                    tankMove(x, y, 2, '^');
                    break;
                case 'D':
                    tankMove(x, y, 0, 'v');
                    break;
                case 'L':
                    tankMove(x, y, 3, '<');
                    break;
                case 'R':
                    tankMove(x, y, 1, '>');
                    break;

            }
        } else if( command == 'S') {
                switch ( map[x][y] ) {
                    case '^':
                        fire(x, y,dh[2],dw[2]);
                        break;
                    case '>':
                        fire(x, y,dh[1],dw[1]);
                        break;
                    case 'v':
                        fire(x, y,dh[0],dw[0]);
                        break;
                    case '<':
                        fire(x, y,dh[3],dw[3]);
                        break;
                }
        }
//        System.out.println("============================");
//        System.out.println("commad : " + command);
//        confirmMap();
    }
    // 움직이는 명령어에 따라 방향 결정하는 메소드
    private static void tankMove(int x, int y, int way, char tank) {
        int nx = x + dh[way];
        int ny = y + dw[way];

        if( isCheck(nx, ny)) {
            if( map[nx][ny] == '.') {
                map[x][y] = '.';
                map[nx][ny] = tank;
            } else {
                map[x][y] = tank;
            }
        } else {
            map[x][y] = tank;
        }
    }
    // 탱크 대포 발사하는 명령어
    private static void fire(int x, int y, int dh, int dw) {
        int nx = x + dh;
        int ny = y + dw;
        if(isCheck(nx,ny)) {
            if( map[nx][ny] == '*') {
                map[nx][ny] = '.';
                return;
            } else if( map[nx][ny] == '#') {
                return;
            } else {
                fire(nx,ny,dh,dw);
            }
        }
        return;
    }
    // 게임의 지도 범위를 벗어났는지 확인하는 메소드
    private static boolean isCheck(int x,int y) {

        if( x >= 0 && x < map.length && y >= 0 && y < map[x].length) {
            return true;
        }
        return false;
    }
}

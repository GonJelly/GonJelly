package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1058 {

    static char[][] friends;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int max = Integer.MIN_VALUE;

        friends = new char[T][T];

        for(int i = 0; i < T; i++) {
            friends[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < T; i++) {
            visit = new boolean[T];
            max = Math.max(max,DFS(i,0));
        }

        System.out.println(max);
    }

    private static int DFS(int v,int two) {

        visit[v] = true;
        for(int i = 0; i < visit.length; i++) {
            if( i == v) {
                continue;
            }
            if( friends[v][i] == 'Y'& visit[i] == false) {
                visit[i] = true;
                two = DFS(i, two + 1);
            }
        }
        return two;
    }
}

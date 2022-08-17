package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1931 {

    private static int n;
    private static int[][] conference;

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/conference_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long start = System.nanoTime();
        n = Integer.parseInt(in.readLine());
        int max = Integer.MIN_VALUE;
        conference = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            conference[i][0] = Integer.parseInt(st.nextToken().trim()); // 시작 시간
            conference[i][1] = Integer.parseInt(st.nextToken().trim()); // 종료 시간
        }

//        for(int j = 0; j < n; j++) {
//            System.out.println(Arrays.toString(conference[j]));
//        }
        Arrays.sort(conference, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if( Integer.valueOf(o1[1]).compareTo(o2[1]) > 0) return 1;
                else if( Integer.valueOf(o1[1]).compareTo(o2[1]) < 0) return -1;
                else return Integer.valueOf(o1[0]).compareTo(Integer.valueOf(o2[0]));
            }
        });

        for(int j = 0; j < n; j++) {
            System.out.println(Arrays.toString(conference[j]));
        }
        do{
            for(int j = 0; j < n; j++) {
                sb.append(Arrays.toString(conference[j])).append(" ");
            }
            sb.append("\n");
            int count = isConference(conference);
            if( count > max ) {
                max = count;
            }
        } while(np(conference));
        System.out.println(sb);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + "ms");
//
//
        System.out.println(max);
        in.close();
    }

    private static boolean np(int[][] conference) {

        int i = n - 1;
        while (i > 0 && conference[i-1][0] >= conference[i][0]) --i;

        if( i == 0) return false;

        int j = n - 1;
        while (conference[i-1][0] >= conference[j][0]) --j;

        swap(conference, i-1, j);

        int k = n - 1;
        while ( i < k) swap(conference, i++, k--);

        return true;
    }

    private static void swap(int[][] conference, int i, int k) {
        int[] temp = conference[i];
        conference[i] = conference[k];
        conference[k] = temp;
    }

    private static int  isConference (int[][] choice) {
        int count = 1;
        for(int i = 1; i < n; i++) {
            if( choice[i][1] <= choice[i+1][0] ) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}

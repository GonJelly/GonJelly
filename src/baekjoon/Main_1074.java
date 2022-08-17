package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1074 {

    private static int count;
    private static int r, c;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int n = (int) Math.pow(2,N);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        count = 0;

        search(0,n,0,n);

    }

    private static void search(int startRow, int endRow,int startCol, int endCol) {
        if( endRow -  startRow == 2 &  endCol - startCol == 2 ) {   // 면적이 2X2면 z자로 이동하면서 r-c 확인
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    count++;
                    if( r == startRow + i && c == startCol + j) {
                        System.out.println(count - 1);
                        return;
                    }
                }
            }
            return;
        }

        int midRow = (startRow + endRow)/2;
        int midCol = (startCol + endCol)/2;

        if( r < midRow && c < midCol ) {            // r / c 의 범위가 1번째 있는지 확인
            search(startRow,midRow,startCol,midCol);
        } else if( r < midRow && c >= midCol) {     // r / c 의 범위가 2번째 있는지 확인
            count += (midRow - startRow) * (midCol - startCol);     // 1번째 면적만큼 추가
            search(startRow,midRow,midCol,endCol);
        } else if( r >= midRow && c < midCol) {     // r / c 의 범위가 3번째 있는지 확인
            count += (midRow - startRow) * (endCol - startCol);     // 1번째 2번째 면적만큼 추가
            search(midRow,endRow,startCol,midCol);
        } else if( r >= midRow && c >= midCol) {    // r / c 의 범위가 4번째 있는지 확인
            count += ((endRow - startRow) * (midCol - startCol)) + ((midRow - startRow) * (midCol - startCol));     // 1번째 2번째 3번째 면적만큼 추가
            search(midRow,endRow,midCol,endCol);
        }


    }
}

package baekjoon.label2000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 도영이가 만든 음식을 먹고싶어*/
public class Main_2961 {
    /* 1초 , 128MB, 1 <= N <= 10 */
    /* 신맛 S 1<= S <= 1_000_000_000 */
    /* 쓴맛 B 1<= B <= 1_000_000_000 */
    private static int foodCnt, sumS,sumB,globalMin;
    private static int[] isUseFood;
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/doyoung_food_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        foodCnt = Integer.parseInt(in.readLine());     // 음식의 갯수
        isUseFood = new int[foodCnt];              // 음식의 사용여부
        globalMin = Integer.MAX_VALUE;

        List<int[]> food = new ArrayList<>(); // 사용할 음식
        for(int i = 1; i <= foodCnt; i++) {   // 사용할 음식 할당
            StringTokenizer st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            food.add(new int[]{S,B});
        }
//        // 음식이 제대로 할당 되어있는 확인
//        StringBuilder sb = new StringBuilder();
//        food.forEach(arr -> {
//            for(int i = 0; i < arr.length; i++) {
//                sb.append(arr[i] + " ");
//            }
//            sb.append("\n");
//        });
//        System.out.println(sb);

        // 요리사작 start
        cooking(food, 0, 0);
        // 요리 끝 end
        System.out.println(globalMin);

    }

    private static void cooking(List<int[]> food, int cnt, int using) {
        if( cnt == foodCnt && using > 0) {
            int sumS = 1;
            int sumB = 0;
            for(int i = 0; i < cnt; i++) {
                if( isUseFood[i] == 1) {
                    sumS *= food.get(i)[0];
                    sumB += food.get(i)[1];
                }
            }
            globalMin = Math.min(globalMin,Math.abs(sumS - sumB));
            return;
        } else if( cnt == foodCnt ) {
            return;
        }

        isUseFood[cnt] = 1;
        cooking(food, cnt + 1, using + 1);
        isUseFood[cnt] = 0;
        cooking(food, cnt + 1, using);

    }
}

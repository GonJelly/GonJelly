package baekjoon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_2477 {

	private static final int SEG = 6;
	private static int[] plus = new int[] {1,-1,1,-1};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 1m^2당 참외의 갯수
		int count = Integer.parseInt(in.readLine());
		int origin = 0;		// 사각형의 넓이
		int vertical = 0;	// 세로로 제일 큰 값
		int horizon = 0;	// 가로로 제일 큰 값
		// 각 변의 길이를 저장할 변수
		int[][] segment = new int[SEG][2];
		// 각 변의 길이를 입력받기
		for(int i = 0; i < SEG; i++) {
			int[] cordinate = new int[] {0, 0};
			// 이전 좌표값에 누적해서 좌표값을 저장
			if ( i != 0 )
				cordinate = Arrays.copyOf(segment[i - 1],2);
			// 입력값을 받아온다.
			List<Integer> read = Arrays.stream(in.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
			// 방향 , 길이
			int direct = read.get(0);
			int length = read.get(1);
			// x축에 값을 누적한다.
			if( direct == 1 || direct == 2 ) {
				vertical = Math.max(vertical, length);
				cordinate[0] += length * plus[direct - 1];
			} else { // y축으로 값을 누적한다.
				horizon = Math.max(horizon, length);
				cordinate[1] += length * plus[direct - 1];
			}
			segment[i] = cordinate;
			// System.out.println(Arrays.toString(segment[i]));
		}
		// 사각형의 넓이를 구한다.
		origin = vertical * horizon;
		// 좌표를 이용해서 밭의 넓이를 구한다.
		int a1 = getVolume(segment[5], segment[0], segment[4]);
		int a2 = getVolume(segment[2], segment[1], segment[3]);
		// 최종적인 밭의 넓이를 저장할 변수 || 사각형의 넓이랑 같다면 뺀다.
		int sum = a1 == origin ? a1 - a2 : a1 + a2;
		System.out.println(sum * count);
	}

	private static int getVolume(int[] mid, int[] first, int[] second) {
		int v = 0;
		int h = 0;;
		v = Math.abs( mid[0] - first[0]) == 0 ? Math.abs(mid[0] - second[0]) : Math.abs( mid[0] - first[0]);
		h = Math.abs( mid[1] - first[1]) == 0 ? Math.abs(mid[1] - second[1]) : Math.abs( mid[1] - first[1]);
		return v * h;
	}

}

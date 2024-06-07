package baekjoon;

import java.util.*;
import java.io.*;

public class Main_2343 {

	private static int lectureCnt, bluelayCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());
		// 	강의 갯수 / 불르레이 갯수
		lectureCnt = Integer.parseInt(st.nextToken());
		bluelayCnt = Integer.parseInt(st.nextToken());
		int maxValue = 0;
		int sum = 0;
		// 강의를 저장할 공간
		st = new StringTokenizer(input.readLine());
		List<Integer> list = new ArrayList();
		// 강의를 입력받는다.
		while(st.hasMoreTokens()) {
			int value = Integer.parseInt(st.nextToken());
			list.add(value);
			maxValue = Math.max(maxValue,value);
			sum += value;
		}
		// 강의를 블루레이에 삽입
		int result = binarySearch(maxValue, sum, list);
		System.out.println(result);
	}

	public static int binarySearch(int low, int high, List<Integer> list) {
		// 기저조건 ( 영상이 only1 || bluelay 갯수가 충족될 경우 )
		if( low > high ) {
			return low;
		}
		// 중간값
		int mid = (low + high) / 2;
		int cd = 0;
		int charge = 0;
		for(int i = 0 ; i < lectureCnt; i++) {
			if( charge + list.get(i) > mid ) {
				cd++;	// 블루레이 갯수 증가
				charge = 0;
			}
			charge += list.get(i);
		}

		if( charge != 0 ) cd++;

		if( cd > bluelayCnt ) {
			return binarySearch(mid + 1, high, list);
		} else {
			return binarySearch(low, mid - 1, list);
		}

	}

}

package baekjoon;

import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;

public class Main_14891 {
	private static String[] gears;
	private static int[] directionArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 톱니바퀴의 상태롤 저장할 이중배열
		gears = new String[4];
		// 배열 초기화
		for(int i = 0, size = gears.length; i < size; i++) {
			gears[i] = br.readLine();
		}
		// 톱니바퀴의 회전 수
		int k = Integer.parseInt(br.readLine());
		// 톱니 바퀴회전 시작 ( worst 100 연산을 시행 )
		for(int i = 0, size = gears.length; i < k; i++) {
			directionArr = new int[4];
			StringTokenizer split = new  StringTokenizer(br.readLine());
			int num = Integer.parseInt(split.nextToken()) - 1;	// 회전 시킬 톱니바퀴 번호
			int direction = Integer.parseInt(split.nextToken()); // 회전시킬 바향
			directionArr[num] = direction;
			// 회전하기 전에 맞닿아 있는 부분 극 비교
			check(num, direction, 1); // 오른쪽 비교
			check(num, direction, -1);// 왼쪽 비교
			// System.out.println("회전 전 : " + Arrays.toString(gears));
			// 톱니바퀴를 회전시킨다.
			for(int j = 0; j < size; j++) {
				int way = directionArr[j];
				if( way == 1	) { // 시계 방향으로 회전
					gears[j] = changeGear(gears[j]);
				} else if( way == -1) { // 반시계 방향으로 회전
					gears[j] = changeGearReverse(gears[j]);
				}
			}
			// System.out.println("회전 후 : " + Arrays.toString(gears));
		}
		// 12방향의 숫자를 확인해서 점수계산
		int sum = 0;
		for(int i = 0, size = gears.length; i < size; i++) {
			int num = Integer.parseInt(gears[i].substring(0,1));
			if( num == 1) {
				sum += (int)Math.pow(2, i);
			}
		}
		System.out.println(sum);
	}

	private static String changeGearReverse(String s) {
		StringBuilder sb = new StringBuilder(s);
		char top = sb.charAt(0);
		sb.replace(0,1,"");
		sb.append(top);
		return sb.toString();
	}

	private static String changeGear(String s) {
		StringBuilder sb = new StringBuilder(s);
		char under = sb.charAt(7);
		sb.replace(7,8,"");
		sb.insert(0, under);
		return sb.toString();
	}

	private static void check(int num, int direction, int way) {
		int next = num + way;
		if( next >= gears.length || next < 0) {
			return;
		}
		// 비교 서로 다른 극이면 회전
		if( way == 1 & gears[num].charAt(2) != gears[next].charAt(6)) {
			directionArr[next] = -direction;
			check(next, -direction, way);
		} else if( way == -1 & gears[num].charAt(6) != gears[next].charAt(2)) {
			directionArr[next] = -direction;
			check(next, -direction, way);
		}
	}
}

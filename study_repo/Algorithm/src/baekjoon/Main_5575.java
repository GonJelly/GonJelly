package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main_5575 {

	private static final int EMP_CNT = 3;

	public static void main(String[] args) throws IOException {

		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		//	직원의 출근시간과 퇴근시간을 저장할 리스트
		List<List<Integer>> emp = new ArrayList();
		for(int i = 0; i < EMP_CNT; i++) {
			List<Integer> temp = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());

			int hour = temp.get(3) - temp.get(0);
			int min = temp.get(4) - temp.get(1);
			if(min < 0) {
				hour -= 1;
				min = 60 - temp.get(1) + temp.get(4);
			}
			int sec = temp.get(5) - temp.get(2);
			if(sec < 0) {
				min -= 1;
				if(min < 0) {
					hour -= 1;
					min += 60;
				}
				sec = 60 - temp.get(2) + temp.get(5);
			}
			System.out.printf("%d %d %d\n",hour,min,sec);
		}

	}
}

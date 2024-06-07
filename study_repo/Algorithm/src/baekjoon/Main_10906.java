package baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main_10906 {

	public static void main(String[] args) throws IOException {

		PriorityQueue<String> dsd = new PriorityQueue<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n-orthotope의 n을 받는다.
		int n = Integer.parseInt(br.readLine());
		// 첫번째 n-orthotope ( 2차원 배열 ) 초기화 [i][0] ==> Si [i][1] ==> Ei
		int[][] first = new int[n][2];
		// 두번째 n-orthotope ( 2차원 배열 ) 초기화 [i][0] ==> Si [i][1] ==> Ei
		int[][] second = new int[n][2];
		// 첫번째, 두번째 n-orthotope 입력값 받아오기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int f = 0;
		while( st.hasMoreTokens() ) {
			first[f][0] = Integer.parseInt(st.nextToken());
			first[f][1] = Integer.parseInt(st.nextToken());
			f++;
		}

		st = new StringTokenizer(br.readLine());
		int s = 0;
		while( st.hasMoreTokens() ) {
			second[s][0] = Integer.parseInt(st.nextToken());
			second[s][1] = Integer.parseInt(st.nextToken());
			s++;
		}

		// 각 지점의 값을 받는다. Si , Ei
		int[][] result = new int[n][2];
		int count = 0;
		// 각 Si,Ei를 비교해서 겹치는 부분을 찾는다.
		for(int i = 0; i < n; i++) {
			int x = -1;
			int y = -1;
			if( first[i][0] > second[i][0]) {
				if(first[i][1] < second[i][1]) {
					x = first[i][0];
					y = first[i][1];
				} else if(first[i][1] > second[i][1]) {
					if(first[i][0] < second[i][1]) {
						x = first[i][0];
						y = second[i][1];
					} else if(first[i][0] > second[i][1]) {
						x = -1;
						y = -1;
					} else {
						x = first[i][0];
						y = -1;
					}
				}
			} else if( first[i][0] < second[i][0]) {
				if(first[i][1] > second[i][1]) {
					x = second[i][0];
					y = second[i][1];
				} else if(first[i][1] < second[i][1]) {
					if(second[i][0] < first[i][1]) {
						x = second[i][0];
						y = first[i][1];
					} else if(second[i][0] > first[i][1]) {
						x = -1;
						y = -1;
					} else {
						x = second[i][0];
						y = -1;
					}
				}
			} else {
				x = first[i][0];
				if( first[i][1] < second[i][1]) {
					y = first[i][1];
				} else {
					y = second[i][1];
				}
			}
			result[i][0] = x;
			result[i][1] = y;
		}

		int point = 0;
		for(int i = 0; i < n; i++) {
			if(result[i][0] != -1) {
				point++;
			}
		}

		System.out.println(count);
	}
}

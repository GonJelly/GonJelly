import java.util.*;
import java.io.*;

public class Main {

	private static int n,m;

	public static void main(String[] args) throws IOException {

		// 입력값을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 학생의 수 n | 보석의 수	m
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 학생의 수
		m = Integer.parseInt(st.nextToken());	// 보석의 수
		// 보석의 수를 저장할 배열
		int[] box = new int[m];
		// 보석의 총 갯수
		int total = 0;
		for(int i = 0; i < m; i++) {
			int jewelry  = Integer.parseInt(br.readLine());
			box[i] = jewelry;
			total = Math.max(total, jewelry);
		}
		Arrays.sort(box);
		int result = binarySearch(1, total, box);
		System.out.println(result);

	}

	public static int binarySearch(int start, int end, int[] box) {

		// 기저조건 ( 더이상 탐색이 안될 때 )
		if( start > end ) {
			return start;
		}
		// 중간 값을 계산 ( 아이들에게 나눠줄 보석의 갯수 )
		int mid = (start + end) / 2;
		// 보석을 나눠줄 수 있는 아이들의 수
		int children = 0;
		// 아이들에게 보석을 나눠준다. ( 1 <= m <= 300_000 )
		for(int i = 0; i < m; i++) {
			int quotient = box[i] / mid;
			if ( box[i] % mid > 0 ) {
				quotient += 1;
			};
			children += quotient;
		}

		if( children > n ) {
			return binarySearch(mid + 1, end, box);
		} else {
			return binarySearch(start, mid - 1, box);
		}
	}

}
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

	private static long n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());	// 나무의 갯수	( 1 ≤ N ≤ 1,000,000 )
		m = Integer.parseInt(st.nextToken());	// 상근이가 가져가야하는 나무의 길이 ( 1 ≤ M ≤ 2,000,000,000 )
		int max = 0;							// 가장 길이가 긴 나무의 길이

		List<Integer> woods = new ArrayList();
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int size = Integer.parseInt(st.nextToken());
			woods.add(size);		// 나중에 절단할 나무들
			if(max < size) {		// 가장 길이가 긴 나무 지정
				max = size;
			}
		}

		// h의 높이를 구한다.
		int low = 0;
		int high = max;
		int mid = 0;
		// lower_bound
		while( low < high ) {
			mid = (low + high) / 2;	// 목재절단기의 높이
			long count = 0;					// 절단한 나무의 총합
			for(int wood : woods) {			// 나무 절단
				count += Math.max((wood - mid), 0);
			}
			// 총합과 가져야가하는 나무 크기 비교
			if( count < m ) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(low - 1);
	}
}
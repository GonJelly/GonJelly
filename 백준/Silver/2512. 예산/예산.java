import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 지바의 수 ( 3 <= 10,000 )
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		//	지방의 요청 예산
		int[] province = new int[n];
		for(int i = 0; i < n; i++) {
			int in = Integer.parseInt(st.nextToken());
			province[i] = in;
			max = Math.max(max,in);
		}
		// 총 예산 n <= total <= 1_000_000_000
		long total = Integer.parseInt(br.readLine());

		// 상한액을 계산하여 그 이상이면 예산요청이면 상한액을 준다.
		// 상한액을 계산하여 그 이하이면 예산요청을 그대로 준다.
		long start = 0;
		long end = ++max;
		while(start < end) {
			long mid = start + (end - start) / 2;	// 상한액
			long temp = 0L;
			for(int money : province) {
				temp += (mid > money ? money : mid);
			}
			if( temp > total ) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(start - 1);
	}
}
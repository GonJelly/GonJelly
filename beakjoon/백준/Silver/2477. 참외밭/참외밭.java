import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	private static final int NUM = 6;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(in.readLine());
		int big = 1;
		int small = 1;
		int[] direct = new int[5];
		int[][] arr = new int[NUM][2];

		for(int i = 0; i < NUM; i++) {
			List<Integer> read = Arrays.stream(in.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
			// 방향과 길이를 저장
			arr[i][0] = read.get(0);
			arr[i][1] = read.get(1);
			// 입력받은 방향 카운트
			direct[read.get(0)]++;
		}

		for(int i = 0; i < NUM; i++) {
			int dir = arr[i][0];
			if(direct[dir] == 1 ) {
				big *= arr[i][1];
				continue;
			}

			int n = ( i + 1 ) % 6;
			int nn = ( i + 2 ) % 6;
			if( arr[i][0] == arr[nn][0] ) {
				small *= arr[n][1];
			}
		}
		System.out.println(cnt * (big - small));
	}
}
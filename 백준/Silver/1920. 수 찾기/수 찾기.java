import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] array = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for( int i = 0; i < n; i++ ) {
			int in = Integer.parseInt(st.nextToken());
			array[i] = in;
		}

		Arrays.sort(array);

		int m = Integer.parseInt(br.readLine());
		int[] targets = new int[m];

		st = new StringTokenizer(br.readLine());
		for( int i = 0; i < m; i++ ) {
			int in = Integer.parseInt(st.nextToken());
			targets[i] = in;
		}

		for(int target : targets) {
			int index = Arrays.binarySearch(array,target);
			System.out.println(index < 0 ? 0 : 1);
		}
	}

}
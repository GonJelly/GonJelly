import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// 1초 / 512MB
	public static void main(String[] args) throws IOException {

		// 입력값을 받을 inputstream
		BufferedInputStream bis = new BufferedInputStream(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		StringTokenizer st = new StringTokenizer(reader.readLine());

		// 1~365일의 일정을 저장할 배열을 생성한다.
		int[] skedule = new int[367];
		// 입력 받은 일정의 갯수를 저장할 변수를 선언한다.
		int n = Integer.parseInt(st.nextToken());
		// 입력을 받을 일정의 시작일 / 종료일을 저장할 이중배열을 선언한다.
		List<int[]> skeduleSaver = new ArrayList<>();
		// 결과값을 저장할 변수
		int result = 0;

		// 입력받은 일정만큼 반복해서 저장한다.
		for(int i = 0; i < n; i++) {
			// 한줄을 읽어온다.
			st = new StringTokenizer(reader.readLine());
			// 시작일과 종료일로 나눠서 저장한다.
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 배열로 저장한다. [0] : 시작일 , [1] : 종료일
			skeduleSaver.add(new int[] {start, end});
		}

		// 시작일 기준으로 오름차순으로 정렬
		Collections.sort(skeduleSaver, (o1,o2) -> {
			if(o1[0] > o2[0]) {
				return 1;
			} else if(o1[0] < o2[0]) {
				return -1;
			} else { // 시간이 같다면 일정이 긴 일정을 먼저 작성한다.
				if (Math.abs(o1[1] - o1[0]) > Math.abs(o2[1] - o2[0])) {
					return -1;
				} else if(Math.abs(o1[1] - o1[0]) < Math.abs(o2[1] - o2[0])) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		// 정렬된 일정들을 스케쥴표에 기입한다.
		for(int[] arr : skeduleSaver) {
			for(int i = arr[0]; i <= arr[1]; i++) {
				skedule[i]++;
			}
		}

		// 길이를 저장할 변수
		int width = 0;
		// 높이를 저장할 변수
		int height = 0;
		// 기입된 일정표를 확인한다.
		for(int number : skedule) {
			// 0이면 초기화해준다.
			if( number == 0 ) {
				result += width * height;
				width = 0;
				height = 0;
			} else {
				// 길이를 늘려준다.
				width++;
				// 제일 높은 높이를 기입한다.
				height = Math.max(height, number);
			}
		}
		System.out.println(result);
	}
}
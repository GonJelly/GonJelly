import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

// 시간 1초 메모리 128MB
public class BinarySearch {

	private static int n, m;
	private static List<Integer> arrA, arrB;

	public static void main(String[] args) throws IOException {

		// 첫번재 입력값 사장님이 보유한 부품의 수 N ( 1 <= n <= 1_000_000 )
		// 두번째 입력값 n개 만큼의 부품들 숫자로 주어짐
		// 세번째 입력값 손님이 요청한 부품의 수 : M ( 1 <= m <= 100_000 )
		// 네번재 입력값 손님이 확인을 요청한 부품들 m개 만큼 주어진다.
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		// 초기화 : 사장님이 가지고 있는 부품의 갯수
		n = Integer.parseInt(input.readLine());
		// 초기화 : 사장님이 가지고 있는 부품들 번호
		arrA = Arrays.stream(input.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		// 초기화 : 손님이 가지고 있는 부품 갯수
		m = Integer.parseInt(input.readLine());
		// 초기화 : 손님이 확인 요청한 부품들의 번호
		arrB = Arrays.stream(input.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		// 부품의 유무를 저장할 공간
		StringBuilder out = new StringBuilder();
		// 사장님이 가지고 있는 부품 정렬
		Collections.sort(arrA);
		// 각 부품이 존재하는 유무체크
		for(int target : arrB) {
			int result = binarySearch(0, n, target);
			out.append(result == -1 ? "no "  : "yes ");
		}
		System.out.println(out);
	}

	public static int binarySearch(int start, int end, int target) {
		// 기저조건
		if( start > end ) {
			return -1;
		}
		// 중간값
		int mid = (start + end) / 2;
		// 해당 부품이 있는지 비교
		if( arrA.get(mid) == target ) {
			return mid;
		} else if( arrA.get(mid) > target ) {
			return binarySearch(start, mid - 1, target);
		} else {
			return binarySearch(start + 1, end, target);
		}

	}
}

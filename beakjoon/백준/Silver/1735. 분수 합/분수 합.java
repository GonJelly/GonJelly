import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> first = Arrays.stream(br.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		List<Integer> second = Arrays.stream(br.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		int x = first.get(0) * second.get(1);
		int _x = second.get(0) * first.get(1);

		int mol = x + _x;
		int over = first.get(1) * second.get(1);

		int gc = gcd(Math.max(mol,over),Math.min(mol,over));

		System.out.printf("%d %d",mol / gc, over / gc);
	}

	public static int gcd(int p, int q) {
		if( q == 0) return p;
		return gcd(q, p%q);
	}

}

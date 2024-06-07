import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(reader.readLine());

		for(int i = 0; i < 5; i++) {
			switch(i) {
				case 0:
				case 1:
				case 3:
					for(int j = 0; j < n; j++) {
						printA();
					}
					break;
				case 2:
				case 4:
					for(int k = 0; k < n; k++) {
						printB();
					}
			}
		}

	}

	private static void printA() {
		for(int i = 0; i < 5; i++) {
			if( i == 0 || i == 4) {
				for(int j = 0; j < n; j++ ) {
					System.out.print("@");
				}
			} else {
				for(int k = 0; k < n; k++) {
					System.out.print(" ");
				}
			}
		}
		System.out.println();
	}

	private static void printB() {
		for(int i = 0; i < 5 * n; i++) {
			System.out.print("@");
		}
		System.out.println();
	}
}
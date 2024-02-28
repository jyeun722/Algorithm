import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 동전 종류
		int K = Integer.parseInt(st.nextToken()); // 합
		int result = 0;

		int[] coins = new int[N];
		for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());
		
		for (int i = N - 1; i >= 0; i--) {
			result += K / coins[i];
			K %= coins[i];
		}

		System.out.println(result);
		br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] candy = new int[N + 2][M + 2];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				candy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] prefix = new int[N + 2][M + 2];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				prefix[i][j] = Math.max(prefix[i - 1][j], prefix[i][j - 1]) + candy[i][j];
			}
		}
		
		System.out.println(prefix[N][M]);
		br.close();
	}
}

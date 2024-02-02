import java.util.*;
import java.io.*;

public class Solution {
	// N: 마을 크기, M: 하나의 집 당 지불 비용, result: 수익 - 운영 비용
	static int N, M, result, cnt;
	static int[] cost; // k 크기 당 운영 비용

	static List<int[]> homes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시 크기
			M = Integer.parseInt(st.nextToken()); // 지불 비용
			result = 0;
			
			cost = new int[N + 2]; // 운영 영역 K 당 운영 비용
			for (int i = 1; i < N + 2; i++)
				cost[i] = i * i + (i - 1) * (i - 1);

            homes = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					int[] home = new int[] {i, j};
					if (num == 1) homes.add(home);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k < N + 2; k++) {
						cnt = 0;
						for (int[] home : homes) {
							if (Math.abs(home[0] - i) + Math.abs(home[1] - j) <= k - 1) {
								cnt++;
							}
						}
						if (cnt > 0 && cnt * M - cost[k] >= 0) {
							if (cnt > result) {
								result = cnt;
							}
						}
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}

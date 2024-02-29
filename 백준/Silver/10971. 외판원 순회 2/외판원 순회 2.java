import java.io.*;
import java.util.*;

public class Main {
	static int N, result;
	static int[][] map;
	static boolean[] visit;

	static void perm(int cnt, int beforeVer, int sum, int first) {
		if (sum >= result) return;
		if (cnt == N - 1) {
			if (map[beforeVer][first] == 0) return;
			result = Math.min(sum + map[beforeVer][first], result);
			return;
		}

		for (int i = 1; i < N; i++) {
			if (visit[i] || map[beforeVer][i] == 0) continue;
			visit[i] = true;
			perm(cnt + 1, i, map[beforeVer][i] + sum, first);
			visit[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new boolean[N];
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			visit[i] = true;
			perm(0, i, 0, i);
			visit[i] = false;
		}

		System.out.println(result);
		br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int w, h, result;
	static int[][] map;

	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static void dfs(int x, int y) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int tx = temp[0];
			int ty = temp[1];

			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = tx + dx[d];
				nextY = ty + dy[d];
				if (nextX < 0 || nextY < 0 || nextX >= h || nextY >= w) continue;
				if (map[nextX][nextY] != 1) continue;
				
				map[nextX][nextY] = 2;
				que.offer(new int[] {nextX, nextY});
			}
		}
	}

	static void findLand() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 1) {
					result++;
					dfs(i, j);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); // 너비
			h = Integer.parseInt(st.nextToken()); // 높이
			result = 0;

			if (w == 0) break;

			map = new int[h][w]; // 1: 땅, 0: 바다
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findLand();
			
			sb.append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}

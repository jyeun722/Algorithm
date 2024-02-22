import java.io.*;
import java.util.*;

public class Main {
	static int N, result;
	static int[][] map; // 0: 바다, 1: 육지

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void bridgeDfs(int x, int y, int landNum) {
		boolean[][] visit = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y, 1 });
		visit[x][y] = true;

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			if (temp[2] >= result) return;

			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = temp[0] + dx[d];
				nextY = temp[1] + dy[d];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if (visit[nextX][nextY]) continue;
				visit[nextX][nextY] = true;
				
				if (map[nextX][nextY] == landNum) continue;
				
				if (map[nextX][nextY] == 0) que.offer(new int[] { nextX, nextY, temp[2] + 1 });
				else {  
					result = Math.min(temp[2], result);
					break;
				}
			}
		}
	}

	static void bridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j + 1 < N) { // 우측 보기
					if (map[i][j] != 0 && map[i][j + 1] == 0) {
						bridgeDfs(i, j + 1, map[i][j]);
					}
				}
				if (i + 1 < N) { // 하단 본기
					if (map[i][j] != 0 && map[i + 1][j] == 0) {
						bridgeDfs(i + 1, j, map[i][j]);
					}
				}
			}
		}
	}

	static int land = 2;
	static void nameDfs(int x, int y) {
		boolean[][] visit = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y });
		visit[x][y] = true;

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			map[temp[0]][temp[1]] = land;

			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = temp[0] + dx[d];
				nextY = temp[1] + dy[d];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if (visit[nextX][nextY]) continue;
				visit[nextX][nextY] = true;
				if (map[nextX][nextY] == 1) que.offer(new int[] { nextX, nextY });
			}
		}
	}

	static void name() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					nameDfs(i, j);
					land++;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		name();
		bridge();

		System.out.println(result);
		br.close();
	}
}

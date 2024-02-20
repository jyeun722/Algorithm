import java.io.*;
import java.util.*;

public class Main {
	static int N, cnt, cnt2;
	static char[][] painting;
	static boolean[][] visitRGB;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static void dfs(int x, int y, char same) {
		// R(빨강) == G(초록), B(파랑)
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y });
		visitRGB[x][y] = true;

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int tx = temp[0];
			int ty = temp[1];

			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = tx + dx[d];
				nextY = ty + dy[d];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if (visitRGB[nextX][nextY]) continue;
				if (painting[nextX][nextY] == same) {
					visitRGB[nextX][nextY] = true;
					que.offer(new int[] {nextX, nextY});
				}
			}
		}
	}
	
	static void RGdfs(int x, int y, char same, char same2) {
		// R(빨강) == G(초록), B(파랑)
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y });
		visitRGB[x][y] = true;

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int tx = temp[0];
			int ty = temp[1];

			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = tx + dx[d];
				nextY = ty + dy[d];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if (visitRGB[nextX][nextY]) continue;
				if (painting[nextX][nextY] == same || painting[nextX][nextY] == same2) {
					visitRGB[nextX][nextY] = true;
					que.offer(new int[] {nextX, nextY});
				}
			}
		}
	}

	static void start() {
		visitRGB = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitRGB[i][j]) {
					dfs(i, j, painting[i][j]);
					cnt++;
				}
			}
		}
		visitRGB = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitRGB[i][j]) {
					if (painting[i][j] == 'B') RGdfs(i, j, 'B', 'B'); 
					else RGdfs(i, j, 'R', 'G');
					cnt2++;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		painting = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				painting[i][j] = str.charAt(j);
			}
		}
		
		cnt = 0;
		cnt2 = 0;
		start();
		
		sb.append(cnt).append(" ").append(cnt2);
		System.out.println(sb);
		br.close();
	}
}

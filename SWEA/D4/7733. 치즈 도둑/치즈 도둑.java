import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] cheese;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static void bfs(boolean[][] visit, int x, int y, int t) {
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
			
			if (cheese[nextX][nextY] >= t && !visit[nextX][nextY]) {
				visit[nextX][nextY] = true;
				bfs(visit, nextX, nextY, t);
			}
		}
	}
	
	static int taste(int t) {
		int cnt = 0;
		boolean[][] visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cheese[i][j] >= t && !visit[i][j]) {
					visit[i][j] = true;
					bfs(visit, i, j, t);
					cnt++;
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(br.readLine());
			
			cheese = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 1;
			for (int i = 1; i <= 100; i++) {
				int temp = taste(i);
				max = Math.max(temp, max);
			}
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}

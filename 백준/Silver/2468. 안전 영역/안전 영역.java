import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static void bfs(boolean[][] visit, int x, int y, int h) {
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
			
			if (map[nextX][nextY] >= h && !visit[nextX][nextY]) {
				visit[nextX][nextY] = true;
				bfs(visit, nextX, nextY, h);
			}
		}
	}
	
	static int height(int h) {
		int tempArea = 0;
		
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= h && !visit[i][j]) {
					visit[i][j] = true;
					bfs(visit, i, j, h);
					tempArea++;
				}
			}
		}

		return tempArea;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxArea = 1;
		for (int h = 1; h <= 100; h++) {
			int temp = height(h);
			if (temp > maxArea) maxArea = temp;
		}
		
		System.out.println(maxArea);
	}
}

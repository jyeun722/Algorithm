import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int cnt = 0;
	static void dfs(boolean[][] visit, int x, int y) {
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
			
			if (!visit[nextX][nextY] && map[nextX][nextY] != 0) {
				visit[nextX][nextY] = true;
				cnt++;
				dfs(visit, nextX, nextY);
			}

		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<Integer> results = new ArrayList<>();
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String home = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = home.charAt(j) - '0';
			}
		}
		
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visit[i][j]) {
					cnt = 1;
					visit[i][j] = true;
					dfs(visit, i, j);
					
					results.add(cnt);
				}
			}
		}
		
		Collections.sort(results);
		sb.append(results.size()).append("\n");
		for (int i = 0; i < results.size(); i++) {
			sb.append(results.get(i)).append("\n");
		}
		System.out.println(sb);
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int N, result;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static void dfs() {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(0, 0, map[0][0]));
		
		int[][] visit = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
		visit[0][0] = map[0][0];
		
		while (!que.isEmpty()) {
			Node node = que.poll();
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = node.x + dx[d];
				nextY = node.y + dy[d];
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				
				if (visit[nextX][nextY] > node.cost + map[nextX][nextY]) {
					visit[nextX][nextY] = node.cost + map[nextX][nextY];
					que.offer(new Node(nextX, nextY, visit[nextX][nextY]));
				}
				
				if (nextX == N - 1 && nextY == N - 1) {
					result = visit[nextX][nextY];
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) break;
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			dfs();
			
			sb.append("Problem ").append(tc++).append(": ").append(result).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}

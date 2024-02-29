import java.io.*;
import java.util.*;

public class Main {
	static class Loc {
		int x, y, kCnt, move;

		public Loc(int x, int y, int kCnt, int move) {
			this.x = x;
			this.y = y;
			this.kCnt = kCnt;
			this.move = move;
		}
	}

	static int K, W, H, result;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	
	static int[] dx2 = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy2 = { -2, -1, 1, 2, -2, -1, 1, 2 };

	static void bfs() {
		ArrayDeque<Loc> que = new ArrayDeque<>();
		que.offer(new Loc(0, 0, 0, 0)); // x, y, 0, 0

		boolean[][][] visit = new boolean[H][W][K + 1];
		visit[0][0][0] = true;

		while (!que.isEmpty()) {
			Loc temp = que.poll();
			
			if (temp.x == H - 1 && temp.y == W - 1) {
				result = Math.min(result, temp.move);
				continue;
			}
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = temp.x + dx[d];
				nextY = temp.y + dy[d];

				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue;
				if (map[nextX][nextY] == 1 || visit[nextX][nextY][temp.kCnt]) continue;
				visit[nextX][nextY][temp.kCnt] = true;
				
				que.offer(new Loc(nextX, nextY, temp.kCnt, temp.move + 1));
			}
			
			if (temp.kCnt < K) {
				for (int d = 0; d < dx2.length; d++) {
					nextX = temp.x + dx2[d];
					nextY = temp.y + dy2[d];

					if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue;
					if (map[nextX][nextY] == 1  || visit[nextX][nextY][temp.kCnt + 1] ) continue;
					visit[nextX][nextY][temp.kCnt + 1] = true;
					
					que.offer(new Loc(nextX, nextY, temp.kCnt + 1, temp.move + 1));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W]; // 0: 아무것도 없는 평지, 1: 장애물
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MAX_VALUE;
		bfs();

		result = result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
		br.close();
	}
}

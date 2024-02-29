import java.io.*;
import java.util.*;

public class Solution {
	static int N, result;
	static int[][] game; // -1: 블랙홀, 0: 빈공간, 1~5: 블록, 6~10: 웜홀
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] block = { {}, { 1, 3, 0, 2 }, { 3, 0, 1, 2 }, { 2, 0, 3, 1 }, { 1, 2, 3, 0 }, { 1, 0, 3, 2 } };
	// 상하좌우(하상우좌)에서 각각 때리는 경우 꺽는 방향 -> 0, 1, 2, 3(상하좌우) 변경되는 방향
	static List<int[]> holl;

	static void move(int x, int y, int direction, int startX, int startY, int cnt) {
		int nextX = x + dx[direction];
		int nextY = y + dy[direction];

		if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) { // 벽에 부딪히는 경우
			int tempD = direction % 2 == 0 ? direction + 1 : direction - 1;
			move(nextX, nextY, tempD, startX, startY, cnt + 1);
		} else if (game[nextX][nextY] == 0) { // 빈공간으로 가는 경우
			if (nextX == startX && nextY == startY) { // 처음이랑 같은 위치인 경우
				result = Math.max(result, cnt);
				return;
			} else { // 처음이랑 다르면 계속 이동
				move(nextX, nextY, direction, startX, startY, cnt);
			}
		} else if (game[nextX][nextY] == -1) { // 블랙홀인 경우
			result = Math.max(result, cnt);
			return;
		} else if (game[nextX][nextY] < 6) { // 블록에 부딪히는 경우
			int tempD = block[game[nextX][nextY]][direction]; // 해당하는 블록이 변경되는 방향
			move(nextX, nextY, tempD, startX, startY, cnt + 1);
		} else { // game[nextX][nextY] >= 6 // 웜홀에 빠진 경우
			for (int i = 0; i < holl.size(); i++) {
				int[] temp = holl.get(i);
				if (temp[2] == game[nextX][nextY] && !(temp[0] == nextX && temp[1] == nextY)) {
					move(temp[0], temp[1], direction, startX, startY, cnt);
					break;
				}
			}
		}
	}

	static void start() { // 블록, 웜홀, 블랙홀 제외하고 출발 -> 빈공간일 때 출발
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (game[i][j] == 0) {
					for (int d = 0; d < dx.length; d++) {
						move(i, j, d, i, j, 0);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			holl = new ArrayList<>();
			game = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					game[i][j] = Integer.parseInt(st.nextToken());

					if (game[i][j] >= 6) { // 웜홀의 경우
						holl.add(new int[] { i, j, game[i][j] });
					}
				}
			}
			result = 0;
			start();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}

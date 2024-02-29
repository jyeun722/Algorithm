import java.io.*;
import java.util.*;

public class Main {
	// .은 빈공간, R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑
	static int result;
	static char[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static void down() {
		char[] store;
		for (int j = 0; j < 6; j++) { // 열마다
			store = new char[12];
			Arrays.fill(store, '.');
			
			// 아래서부터 .이 아닌 애들 store에 넣어주기
			for (int i = 11, idx = 0; i >= 0; i--) { 
				if (map[i][j] != '.') {
					store[idx++] = map[i][j];
				}
			}
			
			// 아래서부터 다시 채워주기
			for (int i = 11, idx = 0; i >= 0; i--) {
				map[i][j] = store[idx++];
			}
		}
	}
	
	static List<int[]> dfs(int x, int y, char color) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { x, y });
		
		boolean[][] visit = new boolean[12][6];
		visit[x][y] = true;
		
		List<int[]> remove = new ArrayList<>();
		
		while (!que.isEmpty()) {
			int[] temp = que.poll();
			remove.add(temp);
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = temp[0] + dx[d];
				nextY = temp[1] + dy[d];
				
				if (nextX < 0 || nextY < 0 || nextX >= 12 || nextY >= 6) continue;
				if (visit[nextX][nextY] || map[nextX][nextY] != color) continue;
				visit[nextX][nextY] = true;
				
				que.offer(new int[] { nextX, nextY });
			}
		}
		
		return remove;
	}
	
	static boolean delete(int x, int y, char color) {
		List<int[]> remove = dfs(x, y, color);
		
		if (remove.size() < 4) return false;
		
		for (int r = 0; r < remove.size(); r++) {
			int[] temp = remove.get(r);
			map[temp[0]][temp[1]] = '.'; // 삭제할 애들 위치에다가 '.' 넣어주기
		}
		
		return true;
	}
	
	static boolean find() { // 찾아서 삭제 후 리스트 추가
		boolean change = false;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] != '.') {
					if (delete(i, j, map[i][j])) {
						change = true;
					}
				}
			}
		}
		down();
		
		return change;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		result = -1;
		
		boolean change;
		do {
			result++;
			change = find();
		} while (change);

		System.out.println(result);
		br.close();
	}
}

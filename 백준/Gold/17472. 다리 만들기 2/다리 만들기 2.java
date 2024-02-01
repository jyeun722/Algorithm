import java.io.*;
import java.util.*;

public class Main {
	static final int MIN_LENGTH = 2;
	static int N, M;
	static int landNum = 2;
	static int[][] island;
	// 0: 바다, 1: 땅
	
	static List<int[]> roads;

	static int sumRoad() {
		Collections.sort(roads, (i1, i2) -> i1[2] - i2[2]);
		int sum = 0;
		if (roads.size() == 0) return -1;

		List<Integer> visits = new ArrayList<>();
		visits.add(roads.get(0)[0]);
		visits.add(roads.get(0)[1]);
		sum += roads.get(0)[2];
		roads.remove(0);
		
		int len = roads.size();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < roads.size(); j++) {
				int x = roads.get(j)[0];
				int y = roads.get(j)[1];
				if (!visits.contains(x) && visits.contains(y)) {
					visits.add(x);
					sum += roads.get(j)[2];
					roads.remove(j);
					break;
				} else if (visits.contains(x) && !visits.contains(y)) {
					visits.add(y);
					sum += roads.get(j)[2];
					roads.remove(j);
					break;
				}
			}
		}
		if (visits.size() != (landNum - 2)) return -1;
		
		return sum;
	}
	
	// d: 0(아래), 1(오른쪽), 2(왼쪽), 3(위)
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, 1, -1, 0};
	static void generateRoad(int x, int y, int d, int islandStart) {
		int cnt = 0;
		int nextX = x, nextY = y;
		while (nextX < N && nextY < M) {
			if (island[nextX][nextY] == 0) { // 바다이면 cnt 증가하고 계속
				cnt++;
				nextX += dx[d];
				nextY += dy[d];
				continue;
			}
			if (island[nextX][nextY] != 0) { // 섬 찾았을 때
				if (cnt < MIN_LENGTH) {
					break;
				}
				else {
					int islandEnd = island[nextX][nextY];
					int[] road = {islandStart, islandEnd, cnt};
					if (islandStart != islandEnd) roads.add(road);
					break;
				}
				
			}
		}
	}
	
	static void findLand() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (island[i][j] != 0) { // 땅일 때
					if (i + 1 < N && island[i + 1][j] == 0) { // 다음 행 바다일 때
						generateRoad(i + 1, j, 0, island[i][j]);
					}
					if (j + 1 < M && island[i][j + 1] == 0) { // 다음 열 바다일 때
						generateRoad(i, j + 1, 1, island[i][j]);
					}
				}
			}
		}
	}
	
	static boolean[][] visited;
	static void bfs(int x, int y) {
		if (x >= N || y >= M || x < 0 || y < 0) return;
		if (visited[x][y]) return;
		if (island[x][y] == 0) return;
		
		visited[x][y] = true;
		island[x][y] = landNum;
		
		for (int i = 0; i < 4; i++) {
			bfs(x + dx[i], y + dy[i]);
		}
	}
	
	static void resetLand() {
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (island[i][j] == 1) {
					bfs(i, j);
					landNum++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		island = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				island[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		roads = new ArrayList<>();
		resetLand();
		findLand();
		
		int result = sumRoad();
		System.out.println(result);
		br.close();
	}
}

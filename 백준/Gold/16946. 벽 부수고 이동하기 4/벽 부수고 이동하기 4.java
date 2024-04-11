import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map; // 0: 이동할 수 있는 곳, 1: 이동할 수 없는 벽이 있는 곳
	static HashMap<Integer, Integer> island;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int cal(int[][] result, HashSet<Integer> set, int x, int y) {
		set = new HashSet<>();
		int nextX, nextY;
		for (int d = 0; d < dx.length; d++) {
			nextX = x + dx[d];
			nextY = y + dy[d];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
			if (result[nextX][nextY] != 0) set.add(result[nextX][nextY]);
		}
		
		int sum = 1;
		for (int key : set) sum += island.get(key);
		return sum;
	}
	
	static int count(int[][] result, int land, int x, int y) {
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {x, y});
		result[x][y] = land;
		int cnt = 1;
		
		while (!que.isEmpty()) {
			int[] temp = que.poll();
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = temp[0] + dx[d];
				nextY = temp[1] + dy[d];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
				if (map[nextX][nextY] == 1 || result[nextX][nextY] != 0) continue;
				result[nextX][nextY] = land;
				que.offer(new int[] {nextX, nextY});
				cnt++;
			}
		}
		return cnt;
	}
	
	static void nameLand() {
		int land = 1;
		island = new HashMap<>();
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && result[i][j] == 0) {
					int cnt = count(result, land, i, j);
					island.put(land++, cnt);
				}
			}
		}
		
		HashSet<Integer> set = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = cal(result, set, i, j) % 10;
				}
			}
		}
	} 
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) {
        		map[i][j] = str.charAt(j) - '0';
        	}
        }
        
        nameLand();
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		sb.append(map[i][j]);
        	}
        	sb.append("\n");
        }
         
        System.out.println(sb);
        br.close();
	}
}

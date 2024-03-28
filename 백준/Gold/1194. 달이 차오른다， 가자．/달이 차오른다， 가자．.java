import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int x, y, key, dis;
		
		public Node(int x, int y, int key, int dis) {
			this.x = x; this.y = y; this.key = key; this.dis = dis;
		}
	}
	static int N, M, result;
	static char[][] map;
	static boolean[][][] visit;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	static void bfs(int[] loc) {
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(loc[0], loc[1], 0, 0));
		
		visit = new boolean[N][M][1 << 6]; // 2^6 == 64
		visit[loc[0]][loc[1]][0] = true;
		
		while (!que.isEmpty()) {
			Node node = que.poll();
			if (node.dis >= result) continue;
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = node.x + dx[d];
				nextY = node.y + dy[d];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
				if (map[nextX][nextY] == '#' || visit[nextX][nextY][node.key]) continue;
				
				if (map[nextX][nextY] == '1') {
					result = node.dis + 1;
					return;
				}
				
				if (map[nextX][nextY] == '.' || map[nextX][nextY] == '0') {
					visit[nextX][nextY][node.key] = true;
					que.offer(new Node(nextX, nextY, node.key, node.dis + 1));
				} else if (map[nextX][nextY] >= 'a' && map[nextX][nextY] <= 'f') {
					int newKey = node.key | (1 << (map[nextX][nextY] - 'a'));
					if (!visit[nextX][nextY][newKey]) {
						visit[nextX][nextY][node.key] = true;
						visit[nextX][nextY][newKey] = true;
						que.offer(new Node(nextX, nextY, newKey, node.dis + 1));
					}
				} else if (map[nextX][nextY] >= 'A' && map[nextX][nextY] <= 'F') { 
					if ((node.key & (1 << (map[nextX][nextY] - 'A'))) > 0) {
						visit[nextX][nextY][node.key] = true;
						que.offer(new Node(nextX, nextY, node.key, node.dis + 1));
					}
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] loc = new int[2];
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) {
        		map[i][j] = str.charAt(j);
        		if (map[i][j] == '0') {
        			loc = new int[] {i, j};
        		} 
        	}
        }
        
        result = Integer.MAX_VALUE;
        bfs(loc);
        
        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);
        br.close();
	}
}

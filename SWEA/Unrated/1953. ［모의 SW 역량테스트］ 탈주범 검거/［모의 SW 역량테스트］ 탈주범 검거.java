import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, L, result;
	static int[][] map;
	static boolean[][] visit;
	static int[][] canGo = {{1, 1, 1, 1}, {1, 1, 0, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}, {0, 1, 0, 1}, {0, 1, 1, 0}, {1, 0, 1, 0}};
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void bfs(int x, int y, int time) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {x, y, time});
		
		while (!que.isEmpty()) {
			int[] temp = que.poll();
			if (temp[2] >= L) continue;
			
			int curX = temp[0], curY = temp[1];
			int curStruc = map[curX][curY];
			int nextX, nextY, nextStruc;
			
			for (int d = 0; d < dx.length; d++) {
				if (canGo[curStruc][d] == 0) continue; 
				
				nextX = curX + dx[d];
				nextY = curY + dy[d];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
				nextStruc = map[nextX][nextY];
				
				if (nextStruc == -1 || visit[nextX][nextY]) continue;
				
				int nextD;
				if (d % 2 == 0) nextD = d + 1;
				else nextD = d - 1;
				
				if (canGo[nextStruc][nextD] == 1) {
					visit[nextX][nextY] = true;
					result++;
					
					int t = temp[2];
					que.offer(new int[] {nextX, nextY, t + 1});
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken()); // 세로
        	M = Integer.parseInt(st.nextToken()); // 가로
        	int R = Integer.parseInt(st.nextToken()); // 맨홀 세로
        	int C = Integer.parseInt(st.nextToken()); // 맨홀 가로
        	L = Integer.parseInt(st.nextToken()); // 탈출 소요 시간
        	
        	result = 1;
        	
        	map = new int[N][M];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < M; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken()) - 1;
        		}
        	}
        	
        	// 0: 터널이 없는 장소, 1~7 구조물 타입
        	visit = new boolean[N][M];
        	visit[R][C] = true;
        	bfs(R, C, 1);
        	 
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
         
        System.out.println(sb);
        br.close();
	}
}

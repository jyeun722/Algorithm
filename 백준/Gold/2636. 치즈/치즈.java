import java.io.*;
import java.util.*;

public class Main {
	static int N, M, count, last, cnt;
	static int[][] cheese;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static void dfs(boolean[][] visit, int x, int y) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[]{x, y});
		
		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int tx = temp[0];
			int ty = temp[1];
			
			int nextX, nextY;
			for (int d = 0; d < dx.length; d++) {
				nextX = tx + dx[d];
				nextY = ty + dy[d];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
				if (visit[nextX][nextY]) continue;
				visit[nextX][nextY] = true;
				if (cheese[nextX][nextY] == 1) cheese[nextX][nextY] = 2;
				else if (cheese[nextX][nextY] == 0) que.offer(new int[] {nextX, nextY});
			}
 		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cheese[i][j] == 2) {
					count--;
					cheese[i][j] = 0;
				}
			}
		}
	}
	
	static void findOne() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 1; j++) {
				if (cheese[i][j + 1] == 1) {
					boolean[][] visit = new boolean[N][M];
					visit[i][j] = true;
					dfs(visit, i, j);
					return;
				}
			}
		}
	}
	
	static void start() {
		while (count != 0) {
			cnt++;
			last = count;
			findOne();
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = 0;
        last = 0;
        cnt = 0;
        
        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		cheese[i][j] = num;
        		if (num == 1) count++;
        	}
        }
        
        start();
        
        sb.append(cnt).append("\n").append(last);
        System.out.println(sb);
        br.close();
	}
}

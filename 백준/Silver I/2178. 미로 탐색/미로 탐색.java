import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int dis;
		public Node(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dis < o.dis ? -1 : 1;
		}
	}
	static int N, M, result;
	static int[][] map;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void bfs(int x, int y, int len) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(x, y, len));
		
		while (!que.isEmpty()) {
			Node temp = que.poll();
			int tempX, tempY;
			
			for (int d = 0; d < dx.length; d++) {
				tempX = temp.x + dx[d];
				tempY = temp.y + dy[d];
				
				if (tempX < 0 || tempY < 0 || tempX >= N || tempY >= M) continue;
				
				if (map[tempX][tempY] == 1) {
					if (tempX == N - 1 && tempY == M - 1) {
						result = temp.dis + 1;
						return;
					} else {
						map[tempX][tempY] = 0;
						que.offer(new Node(tempX, tempY, temp.dis + 1));
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
        result = Integer.MAX_VALUE;
        
        map = new int[N][M]; // 1: 이동 가능한 칸, 0: 이동 불가능한 칸
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) {
        		map[i][j] = str.charAt(j) - '0';
        	}
        }
        
        map[0][0] = 0;
        bfs(0, 0, 1);
         
        System.out.println(result);
        br.close();
	}
}

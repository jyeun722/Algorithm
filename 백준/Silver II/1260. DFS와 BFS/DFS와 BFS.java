import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V; // 정점 개수, 간선 개수, 탐색 시작 정점 vertex
	static int[][] map;
	static StringBuilder sb;
	static boolean[] visit;
	
	static void dfs(int v) {
		sb.append(v).append(" ");
		
		for (int i = 1; i < N + 1; i++) {
			if (visit[i] || i == v) continue;
			if (map[i][v] == 1) {
				visit[i] = true;
				dfs(i);
			}
		}
	}
	
	static void bfs() {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		visit[V] = true;
		que.offer(V);
		
		while (!que.isEmpty()) {
			int ver = que.poll();
			sb.append(ver).append(" ");
			
			for (int i = 1; i < N + 1; i++) {
				if (visit[i] || i == ver) continue;
				if (map[i][ver] == 1) {
					visit[i] = true;
					que.offer(i);
				}
				
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        
        map = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int v = Integer.parseInt(st.nextToken());
        	int v2 = Integer.parseInt(st.nextToken());
        	map[v][v2] = map[v2][v] = 1;
        }
        
        visit = new boolean[N + 1];
        visit[V] = true;
        dfs(V);
        sb.append("\n");
        
        visit = new boolean[N + 1];
        bfs();
         
        System.out.println(sb);
        br.close();
	}
}

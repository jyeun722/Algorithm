import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int ver, dis;
		
		public Node(int dest, int dis) {
			this.ver = dest; this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dis - o.dis;
		}
	}
	
	static void dijkstra(int[][] graph, boolean[] visit, int[] dist, int s, int n) {
    	// 다익스트라
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	dist[s] = 0;
    	pq.offer(new Node(s, dist[s]));
    	
    	while (!pq.isEmpty()) {
    		Node node = pq.poll();
    		
    		int minVertex = node.ver;
    		int min = node.dis;
    		if (visit[minVertex]) continue;
    		
    		visit[minVertex] = true;
//    		if (minVertex == n) break; => 왜 넣으면 에러나는지 이해불가
    		
    		for (int j = 1; j < n + 1; j++) {
    			// 방문 안했고, minVertex에서 j로 가는게 0이 아니고(갈 수 있고),
    			// 현재 j로 가는 가중치보다 현재 가중치 + minVertex에서 j로 가는 가중치가 작다면
    			if (!visit[j] && graph[minVertex][j] != 0 && dist[j] > min + graph[minVertex][j]) {
    				dist[j] = min + graph[minVertex][j];
    				pq.offer(new Node(j, dist[j]));
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
        	int n = Integer.parseInt(st.nextToken()); // 교차로 개수
        	int m = Integer.parseInt(st.nextToken()); // 도로 개수
        	int t = Integer.parseInt(st.nextToken()); // 목적지 후보의 개수
        	
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken()); // 예술가들의 출발지
        	// 현재 지나간 교차로
        	int g = Integer.parseInt(st.nextToken()); 
        	int h = Integer.parseInt(st.nextToken());

        	int[][] graph = new int[n + 1][n + 1];
        	for (int i = 0; i < m; i++) {
            	st = new StringTokenizer(br.readLine());
            	// a와 b사이에 길이 d의 양방향 도로 존재
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	int d = Integer.parseInt(st.nextToken());
            	graph[a][b] = d;
            	graph[b][a] = d;
        	}

        	ArrayList<Integer> des = new ArrayList<>();
        	for (int i = 0; i < t; i++) { // 목적지 후보
        		int x = Integer.parseInt(br.readLine());
        		des.add(x);
        	}
        	Collections.sort(des);
        	
        	// 1. 일반 최적 경로
        	int INF = 200_000_000;
        	boolean[] visit = new boolean[n + 1];
        	int[] dist = new int[n + 1];
        	Arrays.fill(dist, INF);
        	
        	// 그래프, 방문, 누적, 출발지, 개수,
        	dijkstra(graph, visit, dist, s, n);
        	
        	// 2. 필수 방문 => g - h를 무조건 방문하게 하기 위해 음수 넣기
        	int minus = graph[g][h];
        	graph[g][h] = -100;
        	graph[h][g] = -100;
        	
        	visit = new boolean[n + 1];
        	int[] dist2 = new int[n + 1];
        	Arrays.fill(dist2, INF);
        	
        	dijkstra(graph, visit, dist2, s, n); 
        	// 다시 원래로 만들기 위해 100과 원래 가중치를 더해주기
        	for (int i = 0; i < n + 1; i++) dist2[i] += 100 + minus;
        	
         	for (int i = 0; i < des.size(); i++) {
         		int idx = des.get(i); // 목적지 중에서 최적 경로 == 필수 경로가 같으면 넣어주기
        		if (dist2[idx] == dist[idx] && dist[idx] < INF) {
        			sb.append(idx).append(" "); 
        		}
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
        br.close();
	}
}

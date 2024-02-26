import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int vertex;
		int cost;

		public Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", cost=" + cost + "]";
		}
	}

	static int V;
	static List<Node>[] edge;
	static int[] minEdge;
	
	static void dijkstra(int K) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(K, 0));
		boolean[] visit = new boolean[V];
		minEdge[K] = 0;
		
		while (!que.isEmpty()) {
			Node node = que.poll();
			int ver = node.vertex;
			
			if (visit[ver]) continue;
			visit[ver] = true;
			
			for (Node next : edge[ver]) {
				if (minEdge[next.vertex] > minEdge[ver] + next.cost) {
					minEdge[next.vertex] = minEdge[ver] + next.cost;
					
					que.offer(new Node(next.vertex, minEdge[next.vertex]));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine()) - 1;

		edge = new ArrayList[V];
		for (int i = 0; i < V; i++) edge[i] = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int wei = Integer.parseInt(st.nextToken());
			
			edge[start].add(new Node(end, wei));
		}
		
		minEdge = new int[V];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		dijkstra(K);
		
		for (int i = 0; i < V; i++) {
			if (minEdge[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(minEdge[i]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, start, end;
	static ArrayList<Node>[] bus; // 출발 도시, 도착 도시, 버스 비용
	static int[] minEdge;
	
	static class Node implements Comparable<Node> {
		int arrive;
		int cost;
		
		public Node(int arrive, int cost) {
			this.arrive = arrive;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static void load(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>(); // 시작, 도착, 비용
		que.offer(new Node(start, 0));
		boolean[] visit = new boolean[N];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[start] = 0;
		
		while (!que.isEmpty()) {
			Node node = que.poll();
			int arri = node.arrive;
			
			if (visit[arri]) continue;
			visit[arri] = true;
			
			for (Node next : bus[arri]) {
                // 출발 ~ B > 출발 ~ A + A ~ B
				if (minEdge[next.arrive] > minEdge[arri] + next.cost) {
					minEdge[next.arrive] = minEdge[arri] + next.cost;
					
					que.offer(new Node(next.arrive, minEdge[next.arrive]));
				}
			} 
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수

		minEdge = new int[N];
		bus = new ArrayList[N];
		for (int i = 0; i < N; i++) bus[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken());
			
			bus[s].add(new Node(d, v));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		
		load(start);

		System.out.println(minEdge[end]);
		br.close();
	}
}

import java.io.*;
import java.util.*;

public class Solution {
	static int N, cnt; // 섬의 개수
	static double result;
	static double E;
	static int[][] island;

	static class Node implements Comparable<Node> {
		int end;
		double dis;

		public Node(int end, double dis) {
			this.end = end;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return this.dis < o.dis ? -1 : 1;
		}
	}

	static void sumPrim() {
		PriorityQueue<Node> que = new PriorityQueue<>();
		double[] minEdge = new double[N]; // 최소 간선 저장
		Arrays.fill(minEdge, Double.MAX_VALUE);
		minEdge[0] = 0;
		que.offer(new Node(0, minEdge[0]));
		
		boolean[] visit = new boolean[N];

		while (!que.isEmpty()) {
			Node temp = que.poll();
			int minVertex = temp.end;
			double min = temp.dis;
			
			if (visit[minVertex]) continue;
			visit[minVertex] = true;
			
			result += min * E;
			if (cnt++ == N - 1) break;

			for (int j = 0; j < N; j++) {
				double dis = Math.pow(island[0][j] - island[0][minVertex], 2)
						+ Math.pow(island[1][j] - island[1][minVertex], 2);
				if (!visit[j] && minEdge[j] > dis) {
					minEdge[j] = dis;
					que.offer(new Node(j, minEdge[j]));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(br.readLine()); // 섬의 개수
			result = 0;
			cnt = 0;
			
			island = new int[2][N];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			E = Double.parseDouble(br.readLine()); // 환경 부담 세율 -> E * L(거리)^2 지불
			sumPrim();

			sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}
import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int level, no;
		
		public Node(int level, int no) {
			this.level = level; this.no = no;
		}

        @Override
		public int compareTo(Node o) {
			if (this.level < o.level) return -1;
			else if (this.level > o.level) return 1;
			else return this.no - o.no;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		TreeSet<Node> set = new TreeSet<Node>();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken()); // 문제 번호
			int L = Integer.parseInt(st.nextToken()); // 난이도
			set.add(new Node(L, P));
			map.put(P, L);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();

			if (order.equals("add")) { // 추가
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				set.add(new Node(L, P));
				map.put(P, L);
			} else if (order.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) { // 어려운 문제 -> 문제 번호 큰 것
					int problem = set.last().no;
					sb.append(problem).append("\n");
				} else { // -1: 쉬운 문제 -> 문제 번호 작은 것
					int problem = set.first().no;
					sb.append(problem).append("\n");
				}
			} else { // solved: 삭제
				int P = Integer.parseInt(st.nextToken());
				int L = map.get(P);
				map.remove(P);
				set.remove(new Node(L, P));
			}
		}

		System.out.println(sb);
		br.close();
	}
}

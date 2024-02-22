import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, K, A, B, result;
	static int[] a, b, t;
	static List<int[]> end; // 고객 번호, 방문했던 접수 번호, 방문했던 정비 번호
	
	static void sum() {
		for (int i = 0; i < end.size(); i++) {
			int[] cust = end.get(i);
			if (cust[1] == A && cust[2] == B) result += cust[0] + 1;
		}
	}

	static void rotate() {
		int time = 0;

		PriorityQueue<Integer> aWait = new PriorityQueue<>(); // 고객번호가 낮은 순서대로 우선 접수
		ArrayDeque<int[]> bWait = new ArrayDeque<>(); // 먼저 기다리는 고객이 우선

		int[][] aVisit = new int[N][]; // 고객 번호, 시간
		int[][] bVisit = new int[M][]; // 고객 번호, 방문했던 접수 번호, 시간
		
		for (int i = 0; i < N; i++) aVisit[i] = new int[] {-1, -1};
		for (int i = 0; i < M; i++) bVisit[i] = new int[] {-1, -1, -1};
		
		while (true) {
			for (int i = 0; i < t.length; i++) {
				if (t[i] == time)
					aWait.offer(i);
			}

			// 접수 창구
			for (int i = 0; i < N; i++) { // 시간 줄여주고 0인 애들 bWait에 넣어주기
				aVisit[i][1]--; // 시간 감수
				if (aVisit[i][1] == 0) {
					bWait.offer(new int[] { aVisit[i][0], i }); // 고객 번호, 방문했던 접수 번호
					aVisit[i] = new int[] {-1, -1};
				}
			}
			for (int i = 0; i < N; i++) { // 접수 창구번호가 작은 곳
				if (aVisit[i][0] == -1 && !aWait.isEmpty()) { // 창구가 비어있고, 고객이 기다리고 있으면
					int idx = aWait.poll(); // 고객 인덱스 번호 찾아서 넣어주기
					aVisit[i] = new int[] { idx, a[i] }; // 고객 번호, 시간
				}
			}

			// 정비 창구
			for (int i = 0; i < M; i++) { // 시간 줄여주고 0인애들 최종 list에 넣기
				bVisit[i][2]--; // 시간 감소
				if (bVisit[i][2] == 0) {
					end.add(new int[] { bVisit[i][0], bVisit[i][1], i }); // 고객 번호, 방문했던 접수 번호, 방문했던 정비 번호
					bVisit[i] = new int[] {-1, -1, -1};
				}
			}
			for (int i = 0; i < M; i++) { // 정비 창구번호가 작은 곳
				if (bVisit[i][0] == -1 && !bWait.isEmpty()) {
					// 이용했던 접수 창구번호가 작은 고객이 우선 -> 접수에서 낮은 애들부터 봄
					int[] idx = bWait.poll();
					bVisit[i] = new int[] { idx[0], idx[1], b[i] }; // 고객 번호, 방문했던 접수 번호, 시간
				}
			}
			
			if (end.size() == K) break;

			time++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 접수 창구의 개수
			M = Integer.parseInt(st.nextToken()); // 정비 창구의 개수
			K = Integer.parseInt(st.nextToken()); // 차량 정비소를 방문한 고객의 수
			A = Integer.parseInt(st.nextToken()) - 1; // 지갑을 두고 간 고객이 이용한 접수 창구번호
			B = Integer.parseInt(st.nextToken()) - 1; // 지갑을 두고 간 고객이 이용한 정비 창구번호

			a = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				a[i] = Integer.parseInt(st.nextToken());

			b = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				b[i] = Integer.parseInt(st.nextToken());

			t = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++)
				t[i] = Integer.parseInt(st.nextToken());

			result = -1;
			end = new ArrayList<>();
			
			rotate();
			sum();
			
			result = result == -1 ? -1 : result + 1;

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}

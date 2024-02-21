import java.io.*;
import java.util.*;

public class Solution {
	static int N, result;
	static int[][] stairs; // x, y, 길이
	static List<int[]> people; 
	
	static int move(List<Integer> APeople, List<Integer> BPeople) {
		int time = 0;
		List<Integer> AStair = new ArrayList<>(); // A 계단
		List<Integer> BStair = new ArrayList<>(); // B 계단

		while (true) {
			time++; // 시간 증가
			if (time > result) return time; // 가지치기: 최솟값을 넘어가면 반환
			
			// 계단 내려가기
			for (int a = 0; a < AStair.size(); a++) {
				AStair.set(a, AStair.get(a) - 1); // 계단 한칸 내려가기
				if (AStair.get(a) == 0) AStair.remove(a--); // 거리 0이면 계단에서 삭제
			}
			for (int b = 0; b < BStair.size(); b++) { // B도 A계단과 동일하게 처리
				BStair.set(b, BStair.get(b) - 1);
				if (BStair.get(b) == 0) BStair.remove(b--);
			}
			
			// 거리 이동
			for (int p = 0; p < APeople.size(); p++) {
				APeople.set(p, APeople.get(p) - 1); // 한걸음 이동 (거리 감소)
				int person = APeople.get(p);
				
				if (person < 0 && AStair.size() < 3) { 
					AStair.add(stairs[0][2]); // 대기 시간 없고 계단 들어갈 수 있으면 진입
					APeople.remove(p--); // A 목록에서 삭제
				} 
			}
			for (int p = 0; p < BPeople.size(); p++) { // A 계단과 동일한 로직
				BPeople.set(p, BPeople.get(p) - 1);
				int person = BPeople.get(p);
				
				if (person < 0 && BStair.size() < 3) {
					BStair.add(stairs[1][2]);
					BPeople.remove(p--);
				} 
			}
			
			// 방과 계단에 아무도 없으면 멈추기
			if (BPeople.isEmpty() && APeople.isEmpty() && AStair.isEmpty() && BStair.isEmpty()) {
				break;
			}
		}
		return time;
	}
	
	static int[] A, B;
	static void comb(int cnt, int start, int limit) {
		if (cnt == limit) {
			B = new int[people.size() - limit];
			
			boolean[] v = new boolean[people.size()]; // A에 대한 방문 체크 후
			for (int i = 0; i < A.length; i++) v[A[i]] = true;
			
			int idx = 0; // B에 방문 처리되지 않은 요소 주입
			for (int i = 0; i < people.size(); i++) if (!v[i]) B[idx++] = i;
			
			List<Integer> APeople = new ArrayList<>();
			List<Integer> BPeople = new ArrayList<>();
			
			for (int i = 0; i < limit; i++) {
				int[] temp = people.get(A[i]);
				APeople.add(temp[2]); // 인덱스 별 A 계단에 대한 거리 추가
			}
			for (int i = 0; i < people.size() - limit; i++) {
				int[] temp = people.get(B[i]);
				BPeople.add(temp[3]); // 인덱스 별 B 계단에 대한 거리 추가
			}
			
			int value = move(APeople, BPeople); 
			result = Math.min(result, value); // 최솟값 갱신
			
			return;
		}
		for (int i = start; i < people.size(); i++) {
			A[cnt] = i;
			comb(cnt + 1, i + 1, limit);
		}
	}
	
	static void start() {
		for (int i = 0; i < people.size() + 1; i++) {
			A = new int[i]; // 각 인원수는 0 ~ 사람 인원수까지 가능
			comb(0, 0, i);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(br.readLine());
			result = Integer.MAX_VALUE;

			int idx = 0;
			people = new ArrayList<>();
			stairs = new int[2][3];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) { // 1: 사람, 2 이상: 계단의 입구 -> 값: 계단의 길이
					int num = Integer.parseInt(st.nextToken());
					if (num > 1) stairs[idx++] = new int[] { i, j, num }; 
					else if (num == 1) people.add(new int[] { i, j, 0, 0 });
					// 계단일 때 => 0: A, 1: B
					// 사람일 때 => x, y, A까지 이동 거리, B까지 이동 거리
				}
			}

			for (int i = 0; i < people.size(); i++) {
				int[] person = people.get(i);
				int dis = Math.abs(person[0] - stairs[0][0]) + Math.abs(person[1] - stairs[0][1]);
				people.get(i)[2] = dis;
				dis = Math.abs(person[0] - stairs[1][0]) + Math.abs(person[1] - stairs[1][1]);
				people.get(i)[3] = dis;
				// [2] : A까지 이동 거리, [3] : B까지 이동 거리
			}
			
			start();
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}

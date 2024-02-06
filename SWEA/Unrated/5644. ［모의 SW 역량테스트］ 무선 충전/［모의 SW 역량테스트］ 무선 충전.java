import java.io.*;
import java.util.*;

public class Solution {
	static int M, A; // M: 이동 수, A: 배터리 수
	static int[] userA, userB, result;
	static int[][] battery;
	
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	// 배터리: X, Y, C(충전 범위), P(처리량)
	// 0: 이동X, 1: 위, 2: 오른쪽, 3: 아래, 4: 왼쪽
	
	static void move(int idx, int[] locAB) { // 0부터
		int loc = 0;
		locAB[loc] = locAB[loc++] + dx[userA[idx]];
		locAB[loc] = locAB[loc++] + dy[userA[idx]];
		locAB[loc] = locAB[loc++] + dx[userB[idx]];
		locAB[loc] = locAB[loc++] + dy[userB[idx]];
	}
	
	// 선택 가능한 배터리 중 가장 많이 충전할 수 있는 조합 선택
	static void charge(List<Integer> cA, List<Integer> cB, int time) {
		int max = 0;
		for (int i = 0; i < cA.size(); i++) {
			int bcA = cA.get(i);
			for (int j = 0; j < cB.size(); j++) {
				int bcB = cB.get(j);
				if (bcA == bcB) {
					max = Math.max(max, battery[bcA][3]);
				} else {
					max = Math.max(max, battery[bcA][3] + battery[bcB][3]);
				}
			}
		}
		result[time] = max;
	}
	
	// 현재 A, B위치에서 선택 가능한 배터리 확인
	static void locCharge(int[] locAB, int time) {
		List<Integer> cA = new ArrayList<>(Arrays.asList(A));
		List<Integer> cB = new ArrayList<>(Arrays.asList(A));
		
		for (int i = 0; i < A; i++) {
			int dis = Math.abs(battery[i][0] - locAB[0]) + Math.abs(battery[i][1] - locAB[1]);
			if (dis <= battery[i][2]) cA.add(i);
			
			dis = Math.abs(battery[i][0] - locAB[2]) + Math.abs(battery[i][1] - locAB[3]);
			if (dis <= battery[i][2]) cB.add(i);
		}
		charge(cA, cB, time);
	}
	
	static void timeStart() {
		int[] locAB = {0, 0, 9, 9};
		
		locCharge(locAB, 0);
		for (int t = 0; t < M; t++) {
			move(t, locAB);
			locCharge(locAB, t + 1);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken()); // 총 이동 시간
        	A = Integer.parseInt(st.nextToken()); // BC의 개수
        	result = new int[M + 1];
        	
        	userA = new int[M];
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		userA[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	userB = new int[M];
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		userB[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	battery = new int[A + 1][4]; // 배터리 입력: X, Y, C(충전 범위), P(처리량) 
        	for (int i = 0; i < A; i++) { 
        		st = new StringTokenizer(br.readLine());
        		battery[i][1] = Integer.parseInt(st.nextToken()) - 1; // 순서 바꿔서 대입
        		battery[i][0] = Integer.parseInt(st.nextToken()) - 1;
        		for (int j = 2; j < 4; j++) {
        			battery[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}

        	for (int i = 0; i < 4; i++) battery[A][i] = 0;
        	battery[A][2] = 100;
        	
        	timeStart();
        	
        	int answer = 0;
        	for (int i = 0; i < result.length; i++) {
        		answer += result[i];
        	}
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb);
        br.close();
	}
}

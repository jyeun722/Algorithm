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
	
	// A와 B가 이동하는 함수
	static void move(int idx, int[] locAB) { // 0부터
		int loc = 0;
		// A가 이동하는 함수 (dx, dy를 사용)
		locAB[loc] = locAB[loc++] + dx[userA[idx]];
		locAB[loc] = locAB[loc++] + dy[userA[idx]];
		
		// B가 이동하는 함수 (dx, dy를 사용)
		locAB[loc] = locAB[loc++] + dx[userB[idx]];
		locAB[loc] = locAB[loc++] + dy[userB[idx]];
	}
	
	// 선택 가능한 배터리 중 가장 많이 충전할 수 있는 조합 선택해서 result 배열에 반영
	static void charge(List<Integer> cA, List<Integer> cB, int time, int max) {
		// 각각 모두 충전기가 최소 하나씩 있다고 가정, 하나씩 밖에 없는 경우는 locCharge에서 적용 후 갖고 온 max 값으로 대체
		for (int i = 0; i < cA.size(); i++) {
			int bcA = cA.get(i); // A가 충전 가능한 충전기 별로
			for (int j = 0; j < cB.size(); j++) {
				int bcB = cB.get(j); // B가 충전 가능한 충전기에 전부 비교해서
				if (bcA == bcB) { // 두 충전기가 같은 충전기일 때는 절반씩 나누어 가지므로 하나의 처리량에 대해서만 max 비교
					max = Math.max(max, battery[bcA][3]);
				} else { // 두 충전기가 다른 충전기일 때는 두 충전기의 처리량을 모두 합산해서 max 비교
					max = Math.max(max, battery[bcA][3] + battery[bcB][3]);
				}
			}
		}
		result[time] = max; // 결과에 max 변수 값 반영
	}
	
	// 현재 A, B위치에서 선택 가능한 배터리 확인
	static void locCharge(int[] locAB, int time) {
		List<Integer> cA = new ArrayList<>(Arrays.asList()); // A가 충전 가능한 배터리 목록
		List<Integer> cB = new ArrayList<>(Arrays.asList()); // B가 충전 가능한 배터리 목록
		int max = 0; // 충전가능한 max 값 저장할 변수
		
		for (int i = 0; i < A; i++) {
			int dis = Math.abs(battery[i][0] - locAB[0]) + Math.abs(battery[i][1] - locAB[1]);
			if (dis <= battery[i][2]) { // A 위치에서 충전 가능한 배터리이면(거리로 확인) 목록에 넣기
				cA.add(i);
				max = Math.max(battery[i][3], max); // max 적용
			}
			
			dis = Math.abs(battery[i][0] - locAB[2]) + Math.abs(battery[i][1] - locAB[3]);
			if (dis <= battery[i][2]) { // B 위치에서 충전 가능한 배터리이면(거리로 확인) 목록에 넣기
				cB.add(i);
				max = Math.max(battery[i][3], max); // max 적용
			}
		}
		charge(cA, cB, time, max);
	}
	
	// 0초부터 M초까지 충전하고 움직이는 로직 반복하는 함수
	static void timeStart() {
		int[] locAB = {0, 0, 9, 9};
		
		locCharge(locAB, 0); // 처음 0초에도 충전 가능
		for (int t = 0; t < M; t++) { // 1초부터 다시 이동 및 충전 반복
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
        	result = new int[M + 1]; // 0부터 M까지의 시간동안
        	
        	userA = new int[M]; // userA가 이동하는 방향 배열
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		userA[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	userB = new int[M]; // userB가 이동하는 방향 배열
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		userB[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	battery = new int[A][4]; // 배터리 입력: X, Y, C(충전 범위), P(처리량) 
        	for (int i = 0; i < A; i++) { 
        		st = new StringTokenizer(br.readLine());
        		battery[i][1] = Integer.parseInt(st.nextToken()) - 1; // 순서 바꿔서 대입
        		battery[i][0] = Integer.parseInt(st.nextToken()) - 1;
        		for (int j = 2; j < 4; j++) {
        			battery[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
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

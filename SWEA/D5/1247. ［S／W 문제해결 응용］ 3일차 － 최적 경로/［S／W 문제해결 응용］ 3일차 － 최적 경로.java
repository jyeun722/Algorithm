import java.io.*;
import java.util.*;

public class Solution {
	static int N, min;
	static int[] custX, custY, company, home;
	static boolean[] visit;
	
	// 순열 (백트랙킹)
	static void perm(int cnt, int x, int y, int sum) {
		if (cnt == N) { // 마지막 고객에게 도달했을 때 집까지 거리 계산 추가
			int dis = Math.abs(x - home[0]) + Math.abs(y - home[1]);
			min = Math.min(min, dis + sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visit[i]) continue;
			
			// |x1 - x2| + |y1 - y2|
			int dis = Math.abs(x - custX[i]) + Math.abs(y - custY[i]);
			if (sum + dis > min) return;
			// 가지치기: 현재까지 더한 값이 이미 저장된 최소값보다 크면 리턴
			
			visit[i] = true;
			perm(cnt + 1, custX[i], custY[i], sum + dis); // 거리를 합해서 파라미터로 전달
			visit[i] = false;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	N = Integer.parseInt(br.readLine());
        	
        	custX = new int[N]; // 고객의 x값
        	custY = new int[N]; // 고객의 y값
        	
        	st = new StringTokenizer(br.readLine());
        	
        	company = new int[2]; // 회사 x, y값
        	home = new int[2]; // 집 x, y값
        	
        	company[0] = Integer.parseInt(st.nextToken());
        	company[1] = Integer.parseInt(st.nextToken());
        	home[0] = Integer.parseInt(st.nextToken());
        	home[1] = Integer.parseInt(st.nextToken());
        	
        	for (int i = 0; i < N; i++) {
        		custX[i] = Integer.parseInt(st.nextToken());
        		custY[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	visit = new boolean[N];
        	min = Integer.MAX_VALUE;
        	
        	perm(0, company[0], company[1], 0); // 처음 시작값을 회사의 x, y로 시작
        	
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
         
        System.out.println(sb);
        br.close();
	}
}

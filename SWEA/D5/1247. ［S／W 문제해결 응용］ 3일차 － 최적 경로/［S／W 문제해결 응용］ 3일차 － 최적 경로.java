import java.io.*;
import java.util.*;

public class Solution {
	static int N, min;
	static int[] custX, custY, company, home;
	static boolean[] visit;
	
	static void perm(int cnt, int x, int y, int sum) {
        if (sum > min) return;
		if (cnt == N) {
			int dis = Math.abs(x - home[0]) + Math.abs(y - home[1]);
			min = Math.min(min, dis + sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			int dis = Math.abs(x - custX[i]) + Math.abs(y - custY[i]);
			perm(cnt + 1, custX[i], custY[i], sum + dis);
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
        	
        	custX = new int[N];
        	custY = new int[N];
        	
        	st = new StringTokenizer(br.readLine());
        	
        	company = new int[2];
        	home = new int[2];
        	
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
        	
        	perm(0, company[0], company[1], 0);
        	
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
         
        System.out.println(sb);
        br.close();
	}
}

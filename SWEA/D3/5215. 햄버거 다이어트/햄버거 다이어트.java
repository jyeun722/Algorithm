import java.io.*;
import java.util.*;

public class Solution {
	static int N, L, maxTaste;
	static int[][] food;
	
	static void comb(int cnt, int start, int sumT, int sumK) {
        if (sumK > L) return;
		if (sumK <= L) {
			maxTaste = Math.max(sumT, maxTaste);
		}
		for (int i = start; i < N; i++) {
			comb(cnt + 1, i + 1, sumT + food[i][0], sumK + food[i][1]);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	maxTaste = 0;
        	
        	food = new int[N][2];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		food[i][0] = Integer.parseInt(st.nextToken());
        		food[i][1] = Integer.parseInt(st.nextToken());
        	}
        	
        	comb(0, 0, 0, 0);
        	
        	sb.append("#").append(tc).append(" ").append(maxTaste).append("\n");
        }
        
        System.out.println(sb);
        br.close();
	}
}

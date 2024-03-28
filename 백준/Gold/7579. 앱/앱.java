import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
        	memory[i] = Integer.parseInt(st.nextToken());
        }
        
        int limitCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
        	cost[i] = Integer.parseInt(st.nextToken());
        	limitCost += cost[i];
        }
        
        int result = Integer.MAX_VALUE;
        int[] dp = new int[limitCost + 1];
        for (int n = 1; n < N + 1; n++) {
        	for (int c = limitCost; c - cost[n] >= 0; c--) {
        		dp[c] = Math.max(dp[c], memory[n] + dp[c - cost[n]]);
        		if (dp[c] >= M && result > c) {
        			result = c;
        			limitCost = c;
        		}
        	}
        }

        System.out.println(result);
        br.close();
    }
}
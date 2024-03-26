import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] bag = new int[N + 1][2]; // 무게, 가치
        for (int i = 1; i < N + 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 2; j++) {
        		bag[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[][] dp = new int[N + 1][M + 1];
        for (int k = 1; k < N + 1; k++) {
        	dp[k][0] = 0;
        	for (int w = 1; w < M + 1; w++) {
        		if (bag[k][0] > w) {
        			dp[k][w] = dp[k - 1][w];
        		} else {
        			dp[k][w] = Math.max(dp[k - 1][w], dp[k - 1][w - bag[k][0]] + bag[k][1]);
        		}
        	}
        }
        
        System.out.println(dp[N][M]);
        br.close();
	}
}

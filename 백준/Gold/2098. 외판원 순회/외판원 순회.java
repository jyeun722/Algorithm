import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static final int INF = 16_000_000;
	static int[][] map;
	static int[][] dp;
	
	static int dfs(int vertex, int key) {
		if (key == (1 << N) - 1) {
			if (map[vertex][0] != 0) return map[vertex][0]; 
			else return INF;
		}
		
		// 이미 방문한 루투인 경우(dp가 있는 경우) 그대로 반환 
		if (dp[vertex][key] != -1) return dp[vertex][key]; 
		dp[vertex][key] = INF; 
		
		for (int i = 0; i < N; i++) {
			if ((key & (1 << i)) == 0 && map[vertex][i] != 0) {
				dp[vertex][key] = Math.min(dp[vertex][key], dfs(i, key | (1 << i)) + map[vertex][i]);
			}
		}
		return dp[vertex][key];
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        
        int result = dfs(0, 1);
         
        System.out.println(result);
        br.close();
	}
}

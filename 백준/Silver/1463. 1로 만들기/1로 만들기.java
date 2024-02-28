import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 0;
        
        for (int i = 2; i < N + 1; i++) {
            dp[i] = dp[i - 1] + 1; 
            // 1을 더한 경우 횟수가 전 횟수보다 1회 증가
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            // 2으로 나누어 떨어지는 경우 < 1회 증가 횟수 vs 3으로 나누어 떨어졌을 때의 횟수 + 1 >
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            // 3으로 나누어 떨어지는 경우 < 위의 경우 vs 3으로 나누어 떨어졌을 때의 횟수 + 1 >
        }
        System.out.println(dp[N]);
        br.close();
	}
}

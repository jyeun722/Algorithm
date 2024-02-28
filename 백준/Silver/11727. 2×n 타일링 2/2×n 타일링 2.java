import java.io.*;
import java.util.*;

public class Main {
	static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 5;
        
        for (int i = 4; i < N + 1; i++) {
        	dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
        }
         
        System.out.println(dp[N]);
        br.close();
	}
}

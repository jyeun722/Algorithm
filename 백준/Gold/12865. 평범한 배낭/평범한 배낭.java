import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	value[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[K + 1];
        for (int n = 1; n < N + 1; n++) {
        	for(int w = K; w - weight[n] >= 0; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight[n]] + value[n]);
            }
        }
        
        System.out.println(dp[K]);
        br.close();
	}
}

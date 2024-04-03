import java.io.*;
import java.util.*;
  
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
  
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
  
            int[] volume = new int[N + 1];
            int[] value = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                volume[i] = Integer.parseInt(st.nextToken());
                value[i] = Integer.parseInt(st.nextToken());
            }
  
            int[] dp = new int[K + 1];
            for (int n = 1; n < N + 1; n++) {
                for (int w = K; w - volume[n] >= 0; w--) {
                    dp[w] = Math.max(dp[w], value[n] + dp[w - volume[n]]);
                }
            }
  
            sb.append("#").append(tc).append(" ").append(dp[K]).append("\n");
        }
  
        System.out.println(sb);
        br.close();
    }
}
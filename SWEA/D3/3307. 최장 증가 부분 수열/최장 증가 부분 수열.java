import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
             
            int[] arr = new int[N + 1];
            int[] dp = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            int max = 1;
            dp[1] = 1;
            for (int i = 2; i < N + 1; i++) {
                dp[i] = 1;
                for (int j = 1; j < i; j++) {
                    if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
             
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
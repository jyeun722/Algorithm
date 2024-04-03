import java.io.*;
import java.util.*;
 
public class Solution {
    static long cal(int[] F, long[] G, long num) {
        if (num < 0) return 0;
        if (num < 10) return F[(int) num];
         
        // 527의 500부터 계산 => G[2](F[99]) * 5 + F[4]*100 + 5*(27 + 1)
        String forSplit = Long.toString(num);
        int length = forSplit.length();
        int first = forSplit.charAt(0) - '0';
        long remain = Long.parseLong(forSplit.substring(1));
         
        long answer =  G[length - 1] * first
                + F[first - 1] * (long) Math.pow(10, length - 1)
                + first * (remain + 1);
        answer += cal(F, G, remain);
         
        return answer;
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int[] F = new int[10];
        for (int i = 1; i < 10; i++) F[i] = F[i - 1] + i;
         
        // G[1] = F[9]
        // G[2] = F[99] = F[9]*10 + F[9]*10 = 20*F(9)
        // G[3] = F[999] = F[99]*10 + F[9]*10^2 = 4000*F[9]
        long[] G = new long[16];
        G[1] = F[9]; 
        for (int i = 2; i < 16; i++) {
            G[i] = G[i - 1] * 10 + F[9] * (long) Math.pow(10, i - 1);
        }
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
             
            long result = cal(F, G, B) - cal(F, G, A - 1);
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
         
        System.out.println(sb);
        br.close();
    }
}
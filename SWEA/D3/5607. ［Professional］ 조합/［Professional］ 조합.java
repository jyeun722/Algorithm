import java.io.*;
import java.util.*;
 
public class Solution {
    static long nCr(int n, int r, int p) {
        if (r == 0) return 1L;
         
        long[] fac = new long[n + 1];
        fac[0] = 1;
         
        for (int i = 1; i < n + 1; i++) fac[i] = fac[i - 1] * i % p;
         
        return (fac[n] * power(fac[r], p - 2, p )
                % p * power(fac[n - r], p - 2, p) % p) % p;
    }
     
    static long power(long x, long y, long p) {
        long res = 1L;
        x = x % p;
        // => 3^7 > 7 3 1   3^7 --> 3^1 * 3^2 * 3^4
        while (y > 0) {
            if (y % 2 == 1) res = (res * x) % p;
            y = y >> 1; // y = y / 2
            x = (x * x) % p;
        }
        return res;
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
             
            long result = nCr(N, R, 1234567891);
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
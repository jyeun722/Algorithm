import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, M, max;
    static int[] snack;
    static void comb(int cnt, int start, int sum) {
        if (cnt == 2) {
            if (sum > max && sum <= M) max = sum;
            return;
        }
        for (int i = start; i < N; i++) {
            comb(cnt + 1, i + 1, sum + snack[i]);
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            max = -1;
             
            snack = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }
             
            comb(0, 0, 0);
             
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
         
        System.out.println(sb);
        br.close();
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 필요한 메모리 바이트
        int[][] memory = new int[N + 1][2]; // 바이트, 비용

        int limit = 0;
        st = new StringTokenizer(br.readLine());
        for (int j = 1; j < N + 1; j++) {
            memory[j][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int j = 1; j < N + 1; j++) {
            memory[j][1] = Integer.parseInt(st.nextToken());
            limit += memory[j][1];
        }

        int result = Integer.MAX_VALUE;
        int[][] dp = new int[N + 1][limit + 1];
        for (int k = 1; k < N + 1; k++) {
            for (int v = 0; v < limit + 1; v++) {
                if (memory[k][1] > v) {
                    dp[k][v] = dp[k - 1][v];
                } else {
                    dp[k][v] = Math.max(dp[k - 1][v], dp[k - 1][v - memory[k][1]] + memory[k][0]);
                }
                if (dp[k][v] >= M) result = Math.min(v, result);
            }
        }

        System.out.println(result);
        br.close();
    }
}

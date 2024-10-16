import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int subMax = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); // 상담 시간
            int p = Integer.parseInt(st.nextToken()); // 상담 가격

            // i + t = 마무리 되는 날짜, N = 근무 마지막 날짜
            if (i + t > N) {
                subMax = Math.max(subMax, dp[i + 1]);
                continue;
            }

            dp[i + t] = Math.max(dp[i + t], p + subMax); // 이번 상담 가격 + 전날까지 최고값
            subMax = Math.max(subMax, dp[i + 1]);
            answer = Math.max(answer, dp[i + t]);
        }

        System.out.println(answer);

        br.close();
    }
}

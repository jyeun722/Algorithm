import java.util.*;
import java.io.*;

public class Main {
    /*
    1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
    N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
    i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
    */
    static final int INF = 999_999_999;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N(2 ≤ N ≤ 1,000)
        int[][] cost = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];
        int result = INF;

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int start = 0; start < 3; start++) { // 첫번째 집 색
            for (int j = 0; j < 3; j++) {
                if (start == j) dp[1][j] = cost[1][j]; // 첫번째 집의 start에 비용 넣기
                else dp[1][j] = INF; // 첫번째의 집의 start가 아닌 곳에 무한값
            }

            for (int i = 2; i < N + 1; i++) { // 이전 집의 색깔 min값 + 내 색깔
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            // 첫집이랑 마지막집 색깔 비교
            for (int end = 0; end < 3; end++) {
                if (end != start) {
                    result = Math.min(result, dp[N][end]);
                }
            }
        }

        System.out.println(result);
    }
}
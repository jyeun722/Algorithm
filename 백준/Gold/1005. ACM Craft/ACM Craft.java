import java.util.*;
import java.io.*;

public class Main {
    private static int scan(int N, int[] time, List<Integer>[] rule, int W) {
        int timeMax = time[W]; // 최대 건설 시간: 단독 건물일 경우 생각해서 종착지 건설 시간

        Deque<Integer> que = new ArrayDeque<>();
        que.add(W);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[W] = time[W];

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int k : rule[cur]) {
                if (dp[k] < time[k] + dp[cur]) {
                    dp[k] = time[k] + dp[cur];
                    timeMax = Math.max(dp[k], timeMax);
                    que.offer(k);
                }
            }
        }
        return timeMax;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // tc
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물의 개수(건물의 번호는 1번부터 N번)
            int K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙의 총 개수 K

            st = new StringTokenizer(br.readLine());
            int[] time = new int[N + 1]; // 각 건물당 건설에 걸리는 시간
            for (int d = 1; d < N + 1; d++) {
                time[d] = Integer.parseInt(st.nextToken());
            }

            List<Integer>[] rule = new ArrayList[N + 1]; // 건설순서
            for (int i = 1; i < N + 1; i++) rule[i] = new ArrayList<>();

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken()); // 앞건물
                int Y = Integer.parseInt(st.nextToken()); // 뒷건물
                rule[Y].add(X);
            }

            int W = Integer.parseInt(br.readLine()); // 건설해야 할 건물의 번호 W

            int answer = scan(N, time, rule, W);
            sb.append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }
}

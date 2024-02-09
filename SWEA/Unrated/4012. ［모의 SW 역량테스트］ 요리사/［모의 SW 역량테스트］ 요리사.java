import java.io.*;
import java.util.*;

// 남은 벽돌의 개수
public class Solution {
    static int N, result;
    static int[] row, col;

    static void dfs(int idx, int cnt, int all) {
        if (cnt == N / 2) {
//            System.out.println(all);
            result = Math.min(Math.abs(all), result);
            return;
        } else if (idx == N) return;
        dfs(idx + 1, cnt + 1, all - row[idx] - col[idx]);
        dfs(idx + 1, cnt, all);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            result = Integer.MAX_VALUE;

            int all = 0;
            row = new int[N];
            col = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    row[i] += num;
                    col[j] += num;
                    all += num;
                }
            }
//            System.out.println("tc: " + tc);
            dfs(0, 0, all);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

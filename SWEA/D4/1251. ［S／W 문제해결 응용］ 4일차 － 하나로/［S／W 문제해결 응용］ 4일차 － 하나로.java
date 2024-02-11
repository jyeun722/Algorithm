import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static double result;
    static double E;
    static int[][] island;
    static boolean[] visit;

    static void sum() {
        PriorityQueue<double[]> que = new PriorityQueue<>(Comparator.comparing(i -> (i[2])));
        for (int i = 1; i < N; i++)
            que.offer(new double[]{0, i, Math.sqrt(Math.pow(island[0][i] - island[0][0], 2)
                    + Math.pow(island[1][i] - island[1][0], 2))});
        visit[0] = true;

        while (!que.isEmpty()) {
            double[] temp = que.poll();
            int end = (int) temp[1];
            double L = temp[2];

            if (visit[end]) continue;
            visit[end] = true;
            result += L * L * E;

            for (int i = 1; i < N; i++) {
                if (i == end || visit[i]) continue;
                que.offer(new double[]{end, i, Math.sqrt(Math.pow(island[0][i] - island[0][end], 2)
                        + Math.pow(island[1][i] - island[1][end], 2))});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine()); // 섬의 개수
            result = 0;

            visit = new boolean[N];
            island = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine()); // 환경 부담 세율 -> E * L(거리)^2 지불
            sum();

            sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

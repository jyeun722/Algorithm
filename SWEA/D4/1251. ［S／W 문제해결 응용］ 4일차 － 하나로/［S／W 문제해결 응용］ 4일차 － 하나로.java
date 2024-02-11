import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static double result;
    static double E;
    static int[][] island;
    static boolean[] visit;
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    /*
    static void sumPrim() {
        PriorityQueue<double[]> que = new PriorityQueue<>(Comparator.comparing(i -> (i[2])));
        for (int i = 1; i < N; i++)
            que.offer(new double[]{0, i, Math.pow(island[0][i] - island[0][0], 2)
                    + Math.pow(island[1][i] - island[1][0], 2)});
        visit[0] = true;

        while (!que.isEmpty()) {
            double[] temp = que.poll();
            int end = (int) temp[1];
            double L = temp[2];

            if (visit[end]) continue;
            visit[end] = true;
            result += L * E;

            for (int i = 1; i < N; i++) {
                if (i == end || visit[i]) continue;
                que.offer(new double[]{end, i, Math.pow(island[0][i] - island[0][end], 2)
                        + Math.pow(island[1][i] - island[1][end], 2)});
            }
        }
    }
    */

    static void sumKru() {
        PriorityQueue<double[]> que = new PriorityQueue<>(Comparator.comparing(i -> (i[2])));
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                que.offer(new double[]{i, j, Math.pow(island[0][i] - island[0][j], 2)
                        + Math.pow(island[1][i] - island[1][j], 2)});
            }
        }

        while (!que.isEmpty()) {
            double[] temp = que.poll();
            int x = (int) temp[0];
            int y = (int) temp[1];
            if (find(x) != find(y)) {
                union(x, y);
                result += temp[2] * E;
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

            parent = new int[N]; // kru
            for (int i = 0; i < N; i++) parent[i] = i;

            visit = new boolean[N]; // prim

            island = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine()); // 환경 부담 세율 -> E * L(거리)^2 지불
//            sumPrim();
            sumKru();

            sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

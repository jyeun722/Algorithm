import java.io.*;
import java.util.*;

public class Main {
    static int[] dijkstra(int[][] roads, int N, int X) {
        int[] minEdge = new int[N + 1];
        Arrays.fill(minEdge, 100000);

        PriorityQueue<int[]> que = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        minEdge[X] = 0;
        que.offer(new int[]{X, minEdge[X]});

        boolean[] v = new boolean[N + 1];

        while (!que.isEmpty()) {
            int[] cur = que.poll();

            int minVertex = cur[0];
            int min = cur[1];
            if (v[minVertex]) continue;

            v[minVertex] = true;

            for (int j = 1; j < N + 1; j++) {
                if (roads[minVertex][j] != 0 && minEdge[j] > min + roads[minVertex][j]) {
                    minEdge[j] = min + roads[minVertex][j];
                    que.offer(new int[]{j, minEdge[j]});
                }
            }
        }
        return minEdge;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개의 마을
        int M = Integer.parseInt(st.nextToken()); // M개의 단방향 도로
        int X = Integer.parseInt(st.nextToken()); // X번 마을

        int[][] roadsOrder = new int[N + 1][N + 1];
        int[][] roadsReorder = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            roadsOrder[s][e] = t;
            roadsReorder[e][s] = t;
        }

        int[] minEdge1 = dijkstra(roadsOrder, N, X);
        int[] minEdge2 = dijkstra(roadsReorder, N, X);

        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, minEdge1[i] + minEdge2[i]);
        }
        System.out.println(max);

        br.close();
    }
}

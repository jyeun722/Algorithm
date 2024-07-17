import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개의 마을
        int M = Integer.parseInt(st.nextToken()); // M개의 단방향 도로
        int X = Integer.parseInt(st.nextToken()); // X번 마을

        int[][] roads = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(roads[i], 1000000);
//        for (int i = 1; i < N + 1; i++) roads[i] = new ArrayList();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            roads[s][e] = t;
        }

        for (int i = 1; i < N + 1; i++) {
            roads[i][i] = 0;
//            System.out.println(Arrays.toString(roads[i]));
        }

//        int[] minEdge = new int[N + 1];
//        Arrays.fill(minEdge, 100000);

//        PriorityQueue<int[]> que = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
//        minEdge[2] = 0;
//        que.offer(new int[]{2, minEdge[2]});
//
//        boolean[] v = new boolean[N + 1];
//
//        while (!que.isEmpty()) {
//            int[] cur = que.poll();
//
//            int minVertex = cur[0];
//            int min = cur[1];
//            if (v[minVertex]) continue;
//
//            v[minVertex] = true;
//            if (minVertex == N - 1) break;
//
//            for (int j = 1; j < N + 1; j++) {
//                if (!v[j] && roads[minVertex][j] != 0 && minEdge[j] > min + roads[minVertex][j]) {
//                    minEdge[j] = min + roads[minVertex][j];
//                    que.offer(new int[]{j, minEdge[j]});
//                }
//            }
//        }
//        System.out.println(Arrays.toString(minEdge));


        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    roads[i][j] = Math.min(roads[i][j], roads[i][k] + roads[k][j]);
                }
            }
        }

//        for (int i = 1; i < N + 1; i++) {
//            System.out.println(Arrays.toString(roads[i]));
//        }

        int dist = 0;
        for (int i = 1; i < N + 1; i++) {
            dist = Math.max(roads[i][X] + roads[X][i], dist);
        }
        System.out.println(dist);

        br.close();
    }
}

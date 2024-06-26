import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x;
        int dis;

        public Node(int x, int dis) {
            this.x = x;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis < o.dis ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // N (1 ≤ N ≤ 1000): 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // M (1 ≤ M ≤ 100,000): 연결 가능한 선의 수

        int[] minEdge = new int[N];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        boolean[] visit = new boolean[N];

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], 10000000);
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()); // c (1 ≤ c ≤ 10,000): 연결 비용

            if (a == b) continue;
            if (graph[a][b] > c) {
                graph[a][b] = c;
                graph[b][a] = c;
            }
        }

        int result = 0;
        PriorityQueue<Node> que = new PriorityQueue<>();
        minEdge[0] = 0;
        que.offer(new Node(0, minEdge[0]));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            int minVertex = cur.x;
            int min = cur.dis;
            if (visit[minVertex]) continue;

            visit[minVertex] = true;
            result += min;

            for (int j = 0; j < N; j++) {
                if (!visit[j] && graph[minVertex][j] != 0 && minEdge[j] > graph[minVertex][j]) {
                    minEdge[j] = graph[minVertex][j];
                    que.offer(new Node(j, minEdge[j]));
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}

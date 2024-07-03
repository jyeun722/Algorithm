import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int in, out, weight;

        public Node(int in, int out, int weight) {
            this.in = in;
            this.out = out;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int find(int x, int[] parent) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x], parent);
    }

    static void union(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int M = Integer.parseInt(st.nextToken()); // 길의 개수

        int[] parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        Node[] graph = new Node[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken()) - 1;
            int out = Integer.parseInt(st.nextToken()) - 1;
            int wei = Integer.parseInt(st.nextToken());

            graph[i] = new Node(in, out, wei);
        }

        Arrays.sort(graph);

        int result = 0, max = 0;
        for (int i = 0; i < M; i++) {
            if (find(graph[i].in, parent) != find(graph[i].out, parent)) {
                union(graph[i].in, graph[i].out, parent);
                result += graph[i].weight;
                max = Math.max(max, graph[i].weight);
            }
        }

        System.out.println(result - max);

        br.close();
    }
}

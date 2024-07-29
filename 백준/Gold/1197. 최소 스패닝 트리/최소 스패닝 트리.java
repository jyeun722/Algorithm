import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x; this.y = y; this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static void union(int[] parent, int x, int y) {
        x = parent[x];
        y = parent[y];

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[] parent = new int[V + 1];
        for (int i = 0; i < V + 1; i++) parent[i] = i;

        Node[] nodes = new Node[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(A, B, C);
        }
        Arrays.sort(nodes);

        int result = 0;
        for (int i = 0; i < E; i++) {
            if (find(parent, nodes[i].x) != find(parent, nodes[i].y)) {
                union(parent, nodes[i].x, nodes[i].y);
                result += nodes[i].cost;
            }
        }

        System.out.println(result);

        br.close();
    }
}

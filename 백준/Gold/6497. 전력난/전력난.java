import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x, y, z;

        public Node(int x, int y, int z) {
            this.x = x; this.y = y; this.z = z;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.z, o.z);
        }
    }
    static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (y > x) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 집의 수
            int n = Integer.parseInt(st.nextToken()); // 길의 수
            if (m == 0 && n == 0) break;

            int[] parent = new int[m];
            for (int i = 0; i < m; i++) parent[i] = i;

            Node[] nodes = new Node[n];

            int result = 0, all = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                nodes[i] = new Node(x, y, z);
                all += z;
            }

            Arrays.sort(nodes);
            for (int i = 0; i < n; i++) {
                Node node = nodes[i];
                if (find(parent, node.x) != find(parent, node.y)) {
                    union(parent, node.x, node.y);
                    result += node.z;
                }
            }

            sb.append(all - result).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}

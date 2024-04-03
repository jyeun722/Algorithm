import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, E;
    static int[] parent;
    static Edge[] graph;
     
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int wei;
         
        public Edge(int from, int to, int wei) {
            this.from = from;
            this.to = to;
            this.wei = wei;
        }
 
        @Override
        public int compareTo(Edge o) {
            return this.wei < o.wei ? -1 : 1;
        }
    }
     
    static boolean union(int x, int y) {
        int aRoot = find(x);
        int bRoot = find(y);
         
        if (aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
         
        return true;
    }
     
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
             
            parent = new int[N + 1];
            for (int i = 0; i < N + 1; i++) parent[i] = i;
             
            graph = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                 
                graph[i] = new Edge(from, to, weight);
            }
             
            Arrays.sort(graph);
            long result = 0;
            int cnt = 0;
            for (Edge edge : graph) {
                if (union(edge.from, edge.to)) {
                    result += edge.wei;
                    if (++cnt == N - 1) break;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
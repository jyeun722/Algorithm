import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, M;
     
    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return find(parent, parent[x]);
    }
 
    static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            int[] parent = new int[N + 1];
            for (int i = 1; i < N + 1; i++) parent[i] = i;
             
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                 
                if (parent[a] != parent[b]) union(parent, a, b);
            }
             
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i < N + 1; i++) {
                parent[i] = find(parent, i);
                set.add(parent[i]);
            }
 
            sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
        }
 
        System.out.println(sb);
        br.close();
    }
}
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
            sb.append("#").append(tc).append(" ");
             
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // n개의 집합
            M = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
 
            int[] parent = new int[N + 1];
            for (int i = 1; i < N + 1; i++)
                parent[i] = i;
 
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int oper = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
 
                if (oper == 0 && parent[a] != parent[b]) union(parent, a, b);
                else if (oper == 1) {
                    int result;
                    if (find(parent, a) == find(parent, b)) result = 1;
                    else result = 0;
                    sb.append(result);
                }
            }
 
            sb.append("\n");
        }
 
        System.out.println(sb);
        br.close();
    }
}
import java.util.*;
import java.io.*;

public class Main {
    static int N;

    static void union(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int x, int[] parent) {
        if (parent[x] == x) return x;
        return find(parent[x], parent);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) union(i, j, parent);
            }
        }

        int[] cities = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            cities[i] = city;
        }

        for (int i = 0; i < N; i++) {
            parent[i] = find(i, parent);
        }

        boolean check = true;
        for (int i = 0; i < cities.length - 1; i++) {
            // 방문 가능한 곳이면 pass
            if (find(cities[i], parent) == find(cities[i + 1], parent)) continue;
            else {
                check = false;
                break;
            }
        }

        if (check) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        br.close();
    }
}

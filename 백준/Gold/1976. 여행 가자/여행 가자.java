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

    static boolean bfs(int[][] graph, int x, int y) {
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(x);

        boolean[] visit = new boolean[N];
        visit[x] = true;

        while (!que.isEmpty()) {
            int city = que.poll();
            if (city == y) return true;

            for (int i = 0; i < N; i++) {
                if (visit[i]) continue;
                visit[i] = true;

                if (graph[city][i] == 0) continue;
                que.offer(i);
            }
        }
        return false;
    }

    static boolean cityCheck(int[][] graph, int[] cities, boolean[][] visited) {
        for (int i = 0; i < cities.length - 1; i++) {
            // 방문 가능한 곳이면 pass
            if (visited[cities[i]][cities[i + 1]]) continue;

            // 방문 안해봤던 곳
            if (bfs(graph, cities[i], cities[i + 1])) {
                visited[cities[i]][cities[i + 1]] = true;
                visited[cities[i + 1]][cities[i]] = true;
            } else {
                return false;
            }
        }
        return true;
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

//        System.out.println(Arrays.toString(parent));
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        int[] cities = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            cities[i] = city;
        }
//        System.out.println(Arrays.toString(cities));

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


        boolean[][] visited = new boolean[N][N];
//        if (cityCheck(graph, cities, visited)) {
//            System.out.println("YES");
//        } else {
//            System.out.println("NO");
//        }

        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] country;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static boolean dfs(boolean[][] visit, int i, int j) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        ArrayDeque<int[]> que2 = new ArrayDeque<>();
        que.add(new int[]{i, j});
        int sum = 0;

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int x = temp[0];
            int y = temp[1];

            if (visit[x][y]) continue;

            que2.offer(temp);
            visit[x][y] = true;
            sum += country[x][y];

            for (int d = 0; d < dx.length; d++) {
                int nextX = x + dx[d];
                int nextY = y + dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visit[nextX][nextY]) continue;

                int diff = Math.abs(country[x][y] - country[nextX][nextY]);
                if (diff >= L && diff <= R) {
                    que.offer(new int[]{nextX, nextY});
                }
            }
        }

        if (que2.size() == 1) return true;

        int size = sum / que2.size();
        while (!que2.isEmpty()) {
            int[] temp = que2.poll();
            country[temp[0]][temp[1]] = size;
        }
        return false;
    }

    static int culDay() {
        int day = -1;
        boolean check;
        do {
            check = true;
            day++;
            boolean[][] visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        boolean ch = dfs(visit, i, j);
                        if (!ch) check = false;
                    }
                }
            }
        } while (!check);
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        country = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = culDay();

        System.out.println(result);
        br.close();
    }
}

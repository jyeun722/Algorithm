import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] map; // 0: 빈 칸, 1 ~ 5: CCTV, 6: 벽
    static List<int[]> cctv;

    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static void see(int x, int y, int d) {
        int nextX = x, nextY = y;
        while (true) {
            nextX += dx[d];
            nextY += dy[d];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) break;
            if (map[nextX][nextY] == 0) map[nextX][nextY] = -1;
            else if (map[nextX][nextY] == 6) break;
        }
    }

    static void dfs(int index) {
        if (index == cctv.size()) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) sum++;
                }
            }
            result = Math.min(result, sum);
            return;
        }
        int[] cc = cctv.get(index); // x, y, num
        int x = cc[0], y = cc[1], num = cc[2];

        if (cc[2] == 1) { // 1번 시작
            for (int i = 0; i < 4; i++) {
                int[] temp = new int[N];
                for (int t = 0; t < N; t++) temp[t] = map[t][y];
                int[] temp2 = Arrays.copyOf(map[x], M);

                see(x, y, i);

                if (index < cctv.size()) dfs(index + 1);
                for (int t = 0; t < N; t++) map[t][y] = temp[t];
                map[x] = Arrays.copyOf(temp2, M);
            }
        } else if (num == 2) { // 2번 시작
            // 상하 - 좌우
            int[] temp = new int[N];
            for (int t = 0; t < N; t++) temp[t] = map[t][y];
            int[] temp2 = Arrays.copyOf(map[x], M);

            see(x, y, 2);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 0);
            see(x, y, 1);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);
        } else if (num == 3) { // 3번 시작
            // 상좌 - 상우 - 하좌 - 하우
            int[] temp = new int[N];
            for (int t = 0; t < N; t++) temp[t] = map[t][y];
            int[] temp2 = Arrays.copyOf(map[x], M);

            see(x, y, 0);
            see(x, y, 2);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 0);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 1);
            see(x, y, 2);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 1);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);
        } else { // cc[2] == 4
            // 상좌우 - 하좌우 - 상하좌 - 상하우
            int[] temp = new int[N];
            for (int t = 0; t < N; t++) temp[t] = map[t][y];
            int[] temp2 = Arrays.copyOf(map[x], M);

            see(x, y, 0);
            see(x, y, 2);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 1);
            see(x, y, 2);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 0);
            see(x, y, 1);
            see(x, y, 2);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);

            see(x, y, 0);
            see(x, y, 1);
            see(x, y, 3);

            if (index < cctv.size()) dfs(index + 1);
            for (int t = 0; t < N; t++) map[t][y] = temp[t];
            map[x] = Arrays.copyOf(temp2, M);
        }
    }

    static void five(int x, int y) {
        for (int d = 0; d < dx.length; d++) {
            int nextX = x, nextY = y;
            while (true) {
                nextX += dx[d];
                nextY += dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) break;
                if (map[nextX][nextY] == 0) map[nextX][nextY] = -1;
                else if (map[nextX][nextY] == 6) break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        map = new int[N][M];
        cctv = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num > 0 && num < 5) cctv.add(new int[]{i, j, num});
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 5) five(i, j);
            }
        }

        dfs(0);

        System.out.println(result);
        br.close();
    }
}

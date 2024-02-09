import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] box;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int dfs(ArrayDeque<int[]> tomatoes) {
        int day = 0;
        while (!tomatoes.isEmpty()) {
            int cnt = 0;
            day++;
            int size = tomatoes.size();
            for (int i = 0; i < size; i++) {
                int[] tomato = tomatoes.poll();

                for (int d = 0; d < dx.length; d++) {
                    int nextX = tomato[0] + dx[d];
                    int nextY = tomato[1] + dy[d];

                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;

                    if (box[nextX][nextY] == 0) {
                        box[nextX][nextY] = 1;
                        tomatoes.offer(new int[]{nextX, nextY});
                        cnt++;
                    }
                }
            }
            if (cnt == 0) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (box[i][j] == 0) return -1;
                    }
                }
            }
        }
        return day - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int result;

        ArrayDeque<int[]> tomatoes = new ArrayDeque<>();

        box = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토 없는 칸
                int num = Integer.parseInt(st.nextToken());
                box[i][j] = num;
                if (num == 1) tomatoes.offer(new int[]{i, j}); // x, y
            }
        }

        result = dfs(tomatoes);

        System.out.println(result);
        br.close();
    }
}

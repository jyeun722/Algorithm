import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] area;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dx2 = {1, 0, -1, 0};
    static int[] dy2 = {0, 1, 0, -1};

    static void airCleaner() {
        int row = 0;
        for (int i = 0; i < R; i++) {
            if (area[i][0] == -1) {
                row = i;
                break;
            }
        }

        int x = row - 1;
        int y = 0;
        for (int d = 0; d < dx.length; ) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if (nextX < 0 || nextY < 0 || nextX > row || nextY >= C) {
                d++;
                continue;
            }

            area[x][y] = area[nextX][nextY];
            x = nextX;
            y = nextY;
        }

        x = row + 2;
        y = 0;
        for (int d = 0; d < dx2.length; ) {
            int nextX = x + dx2[d];
            int nextY = y + dy2[d];

            if (nextX <= row || nextY < 0 || nextX >= R || nextY >= C) {
                d++;
                continue;
            }

            area[x][y] = area[nextX][nextY];
            x = nextX;
            y = nextY;
        }

        for (int i = 0; i < 2; i++) {
            area[row + i][0] = -1;
            area[row + i][1] = 0;
        }
    }

    static void spreadDust() {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (area[i][j] != 0) {
                    int cnt = 0;
                    int diff = area[i][j] / 5;
                    for (int d = 0; d < dx.length; d++) {
                        int nextX = i + dx[d];
                        int nextY = j + dy[d];

                        if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C || area[nextX][nextY] == -1) continue;

                        que.add(new int[]{nextX, nextY, diff});
                        cnt++;
                    }

                    area[i][j] -= diff * cnt;
                }
            }
        }

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            area[temp[0]][temp[1]] += temp[2];
        }
    }

    static void startTime() {
        for (int t = 0; t < T; t++) {
            spreadDust(); // 미세먼지 확산
            airCleaner(); // 공기 청정
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        area = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        startTime();

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (area[i][j] != 0) result += area[i][j];
            }
        }

        System.out.println(result + 2);
        br.close();
    }
}

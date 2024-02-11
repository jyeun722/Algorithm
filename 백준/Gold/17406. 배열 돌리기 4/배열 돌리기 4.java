import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, rotateCnt, result;
    static int[][] arr, basic;
    static int[][] opers;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static void rotate(int[][] temp, int idxX, int idxY, int n, int m, int cnt) {
        if (cnt > rotateCnt) return;
        int curX = idxX;
        int curY = idxY;

        for (int d = 0; d < dx.length; ) {
            int nextX = curX + dx[d];
            int nextY = curY + dy[d];

            if (nextX < idxX || nextY < idxY || nextX >= n - cnt || nextY >= m - cnt) {
                d++;
                continue;
            }

            temp[nextX][nextY] = arr[curX][curY];

            curX = nextX;
            curY = nextY;
        }

        rotate(temp, idxX + 1, idxY + 1, n, m, cnt + 1);
    }

    static boolean[] visit;
    static int[] operation;

    static void perm(int cnt) {
        if (cnt == K) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = basic[i][j];
                }
            }
            for (int k = 0; k < K; k++) {
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        arr[a][b] = temp[a][b];
                    }
                }

                int idx = operation[k];
                int r = opers[idx][0];
                int c = opers[idx][1];
                int s = opers[idx][2];
                rotateCnt = 2 * s + 1;
                rotateCnt = rotateCnt % 2 == 0 ? rotateCnt / 2 : rotateCnt / 2 + 1;
                
                rotate(temp, r - s - 1, c - s - 1, r + s, c + s, 0);
            }

            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += temp[i][j];
                }
                result = Math.min(sum, result);
            }
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            operation[cnt] = i;
            perm(cnt + 1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 연산 개수
        result = Integer.MAX_VALUE;

        arr = new int[N][M];
        basic = new int[N][M]; // 원 상태
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                basic[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        opers = new int[K][3];
        visit = new boolean[K];
        operation = new int[K];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            for (int q = 0; q < 3; q++) {
                opers[k][q] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);

        System.out.println(result);
        br.close();
    }
}
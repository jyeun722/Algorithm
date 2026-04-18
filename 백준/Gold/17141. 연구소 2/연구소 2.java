import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static int N, M, virusCnt;
    static int[][] virus, arr, realVirus;
    static int min = Integer.MAX_VALUE;
    static int fill = 0, zero = 0, dfsTime;

    public static void comb(int cnt, int start) {
        if (cnt == M) {
            // bfs
            int[][] his = new int[N][N]; // 각 바이러스 조합마다 0 인지점 초기화해줌
            for (int i = 0; i < N; i++) {
                Arrays.fill(his[i], -1);
            }
            for (int i = 0; i < M; i++) {
                his[realVirus[i][0]][realVirus[i][1]] = 0;
            }

            fill = 0;
            dfsTime = 0;
            dfs(his, 0);
            if (fill == zero) min = Math.min(min, dfsTime);

            return;
        }
        for (int i = start; i < virusCnt; i++) {
            realVirus[cnt] = virus[i];
            comb(cnt + 1, i + 1);
        }
    }

    public static void dfs(int[][] his, int time) {
        if (time >= min) return;

        int dfsFill = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (his[i][j] == time) {
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                        if (arr[nextX][nextY] == 1 || his[nextX][nextY] != -1) continue;

                        his[nextX][nextY] = time + 1;
                        dfsTime = Math.max(dfsTime, time + 1);
                        fill++;
                        dfsFill++;
                    }
                }
            }
        }

        if (dfsFill != 0) dfs(his, time + 1);
    }

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 첫째 줄에 연구소의 크기 N(5 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virus = new int[10][2];
        realVirus = new int[M][2];
        virusCnt = 0;

        // 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) zero++;
                if (arr[i][j] == 2) virus[virusCnt++] = new int[] {i, j};
            }
        }
        zero += virusCnt - M;
        comb(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

        br.close();
    }
}
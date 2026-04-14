import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = new int[] {0, 1}; // 오른쪽, 아래
    static int[] dy = new int[] {1, 0};
    static String answer = "Hing";

    static void dep(int x, int y, int[][] arr, int[][] his, int N) {
        his[x][y] = -1;
        if (arr[x][y] == -1) {
            answer = "HaruHaru";
            return;
        }

        if (arr[x][y] == 0) return;

        for (int i = 0; i < 2; i++) {
            int nextX = x + dx[i] * arr[x][y];
            int nextY = y + dy[i] * arr[x][y];

            if (nextX > N - 1 || nextY > N - 1 || his[nextX][nextY] == -1) continue;
            dep(nextX, nextY, arr, his, N);
        }
    }

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 게임판의 승리 지점(오른쪽 맨 아래 칸)에는 -1이 쓰여있고, 나머지 칸에는 0 이상 100 이하의 정수
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[][] his = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dep(0, 0, arr, his, N);
        System.out.println(answer);

        br.close();
    }
}
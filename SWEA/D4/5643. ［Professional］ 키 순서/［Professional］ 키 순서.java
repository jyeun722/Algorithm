import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, result;
    static boolean[][] heightBig, heightSmall;

    static void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (heightBig[j][i]) {
                    for (int k = 0; k < N; k++) {
                        if (heightBig[i][k]) heightBig[j][k] = true;
                    }
                }
                if (heightSmall[j][i]) {
                    for (int k = 0; k < N; k++) {
                        if (heightSmall[i][k]) heightSmall[j][k] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) if (heightBig[i][j]) cnt++;
            for (int j = 0; j < N; j++) if (heightSmall[i][j]) cnt++;
            if (cnt + 1 == N) result++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine()); // 학생 수
            M = Integer.parseInt(br.readLine()); // 비교한 횟수

            result = 0;

            heightBig = new boolean[N][N]; // 1차원 배열 값보다 더 큰 값들을 참
            heightSmall = new boolean[N][N]; // 1차원 배열 값보다 더 작은 값들을 참
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int big = Integer.parseInt(st.nextToken()) - 1;
                int small = Integer.parseInt(st.nextToken()) - 1;

                heightBig[small][big] = true;
                heightSmall[big][small] = true;
            }

            check();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
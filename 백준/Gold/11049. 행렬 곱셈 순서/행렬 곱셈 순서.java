import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] rc = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rc[i][0] = Integer.parseInt(st.nextToken());
            rc[i][1] = Integer.parseInt(st.nextToken()); 
        }

        // DP[i][j] = i번째 행렬부터 j번째 행렬까지 곱하는 데 필요한 최소 곱셈 횟수
        int[][] DP = new int[N][N];

        // len: 곱셈 구간의 길이 (2부터 N까지)
        for (int len = 2; len < N + 1; len++) {
            // i: 곱셈 구간의 시작 인덱스 (0부터 시작)
            for (int i = 0; i < N - len + 1; i++) {
                // j: 곱셈 구간의 끝 인덱스
                int j = i + len - 1;
                DP[i][j] = Integer.MAX_VALUE;

                // k: i~j 사이에서 곱셈을 나누는 지점 (i ≤ k < j)
                for (int k = i; k < j; k++) {
                    // 왼쪽 구간 + 오른쪽 구간 + 두 행렬 곱셈 비용
                    int cost = DP[i][k] + DP[k + 1][j] + rc[i][0] * rc[k][1] * rc[j][1];
                    DP[i][j] = Math.min(DP[i][j], cost);
                }
            }
        }

        // 전체 행렬 곱셈 최소 연산 횟수 출력
        System.out.println(DP[0][N - 1]);
    }
}

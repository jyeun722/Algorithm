import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int INF = 16_000_000;
    static int[][] map; // map[i][j] : 도시 i → j 이동 비용
    static int[][] dp; // dp[vertex][key] : 현재 vertex 도시에 있고, key 비트마스크를 방문한 상태일 때 남은 최소 비용

    // vertex : 현재 위치한 도시, key : 지금까지 방문한 도시 정보를 비트마스크로 표현한 값
    static int dfs(int vertex, int key) {
        // (1 << N) - 1은 모든 도시를 방문한 상태 (예: N=4면 1111)
        if (key == (1 << N) - 1) {
            // map[vertex][0] != 0 이면 0번 도시로 돌아갈 수 있으므로 그 비용 반환
            if (map[vertex][0] != 0) return map[vertex][0];
            else return INF; // 갈 수 없는 경로이므로 INF 반환
        }

        // 이미 방문한 루트인 경우(dp가 있는 경우) 그대로 반환:-1은 밑에서 방문 안한 상태로 초기화 한 것
        if (dp[vertex][key] != -1) return dp[vertex][key];
        // dp[vertex][key]: 방문 안한 상태의 값: 최솟값 계산을 위한 초기값으로 갱신 후 밑에서 min 으로 구함
        dp[vertex][key] = INF;

        for (int i = 0; i < N; i++) {
            // (key & (1 << i)) == 0 : 아직 도시 i를 방문하지 않은 경우
            // map[vertex][i] != 0 : 현재 도시에서 도시 i로 가는 경로가 존재하는 경우
            if ((key & (1 << i)) == 0 && map[vertex][i] != 0) {
                // 도시 i로 이동하고, 방문 상태에 도시 i를 추가(key | (1 << i))
                // 비용은 현재 → i까지 비용(map[vertex][i]) + i 이후 경로의 최소 비용(dfs(i, key | (1 << i)))
                dp[vertex][key] = Math.min(dp[vertex][key], map[vertex][i] + dfs(i, key | (1 << i)));
            }
        }
        return dp[vertex][key];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N = 4일 때 1 << N: 16, (1 << N) - 1: 15
        // 16 = 10000, 15 = 1111 : 전체 마스킹된 값이 필요
        dp = new int[N][(1 << N) - 1];
        // 모든 DP 값을 -1로 초기화 (아직 방문 X 상태)
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        // 0번 도시에서 출발, key = 1 ⇒ 0번 도시만 방문한 상태 (0001)
        // 어떤 지점에서 출발해도 최소 비용 루트는 동일
        int result = dfs(0, 1);

        System.out.println(result);
        br.close();
    }
}
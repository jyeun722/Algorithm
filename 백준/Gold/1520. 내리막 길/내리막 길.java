import java.io.*;
import java.util.*;

public class Main {
	static int allCount;
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상 
	static int[] dy = {1, 0, -1, 0};
	
	static boolean dfs(int[][] map, int[][] dp, int M, int N, int x, int y) {
		if (x == M - 1 && y == N - 1) { // 도착지 도달
			allCount++; // 카운트 증가
			return true; // 도착했으므로 true 리턴
		}
		
		if (dp[x][y] > 0) { // 이미 지나갔는데, 길이 있었을 경우
			allCount += dp[x][y]; // 해당 루트에서 가능한 길이만큼 카운트 증가
			return true; 
		}
		
		if (dp[x][y] == -1) { // 이미 지나갔는데, 길이 없었을 경우
			return false;
		}
		
		int nextX, nextY;
		for (int d = 0; d < dx.length; d++) {
			nextX = x + dx[d];
			nextY = y + dy[d];
			
			if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) continue;
			if (map[nextX][nextY] < map[x][y]) { // 다음 높이가 더 작은 경우만 갈 수 있음
				// 갈 수 있는 길인지, 없는 길인지 반환
				boolean result = dfs(map, dp, M, N, nextX, nextY);
				if (result) { // 갈 수 있는 길의 경우
					if (dp[nextX][nextY] == 0) dp[x][y]++; // 도착지에서 온 경우 1만 증가
					else dp[x][y] += dp[nextX][nextY]; // 중간에 dp로 만난 경우 그 카운트만큼 증가
				}
			}
		}
		
		if (dp[x][y] > 0) {
			// 사방탐색 했을 때 갈 수 있던 길이 있었던 경우
			return true;
		} else {
			// 갈 수 없는 경우 -1로 바꿔줌
			dp[x][y] = -1;
			return false;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 세로
        int N = Integer.parseInt(st.nextToken()); // 가로
        
        int[][] map = new int[M][N];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
//        for (int i = 0; i < M; i++) System.out.println(Arrays.toString(map[i]));
        
        allCount = 0;
        int[][] dp = new int[M][N];
        dfs(map, dp, M, N, 0, 0);
        
//        System.out.println();
//        for (int i = 0; i < M; i++) System.out.println(Arrays.toString(dp[i]));
        
        System.out.println(allCount);
        br.close();
	}
}
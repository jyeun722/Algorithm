import java.io.*;
import java.util.*;

public class Main {
	static int N, result; // 총 N명, result: 최소 차이
	static int[] teamA;
	static int[][] link;

	static void comb(int cnt, int start) {
		if (cnt == N / 2) { // teamA 만드는 함수
			int[] teamB = new int[N / 2];
			boolean[] visit = new boolean[N];
			
			int sumA = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					sumA += link[teamA[i]][teamA[j]];
				}
				visit[teamA[i]] = true;
			}
			
			for (int i = 0, j = 0; i < N; i++) {
				if (!visit[i]) teamB[j++] = i;
			}
			
			int sumB = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					sumB += link[teamB[i]][teamB[j]];
				}
			}
			
			result = Math.min(Math.abs(sumA - sumB), result);
			return;
		}
		for (int i = start; i < N; i++) {
			teamA[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        
        teamA = new int[N / 2];
        link = new int[N][N];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		link[i][j] = num;
        	}
        }
        
        comb(0, 0);
        System.out.println(result);
        br.close();
	}
}

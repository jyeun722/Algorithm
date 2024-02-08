import java.io.*;
import java.util.*;

public class Main {
	static int N, result; // 총 N명, result: 최소 차이
	static int[] teamA;
	static ArrayList<Integer> teamB;
	static int[][] link;
	
	static int powerPlus(int idx, int team) {
		int sum = 0;
		if (team == 0) {
			for (int i = 0; i < N / 2; i++) {
				sum += link[idx][teamA[i]];
			}
		} else {
			for (int i = 0; i < N / 2; i++) {
				sum += link[idx][teamB.get(i)];
			}
		}
		return sum;
	}
	
	static void comb(int cnt, int start) {
		if (cnt == N / 2) { // teamA 만드는 함수
			teamB = new ArrayList<>();
			for (int i = 0; i < N; i++) teamB.add(i);
			
			int sumA = 0;
			for (int i = 0; i < N / 2; i++) {
				sumA += powerPlus(teamA[i], 0);
				teamB.remove((Integer) teamA[i]);
			}
			
			int sumB = 0;
			for (int i = 0; i < N / 2; i++) {
				sumB += powerPlus(teamB.get(i), 1);
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

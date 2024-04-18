import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, 0, 0}; // N
	static int[] dy = {0, 1, 2, 3}; // N - 3
	
	static int[] dx2 = {0, 1, 2, 3}; // N - 3
	static int[] dy2 = {0, 0, 0, 0}; // N
	
	static int[][] dx3 = {{0, 0, 1, 1}, {0, 0, 0, 1}, {0, 1, 1, 1}, {0, 0, 0, 1}, {0, 1, 1, 1}}; // N - 1
	static int[][] dy3 = {{0, 1, 1, 2}, {0, 1, 2, 2}, {0, 0, 1, 2}, {0, 1, 2, 1}, {1, 0, 1, 2}}; // N - 2
	
	static int[][] dx4 = {{0, 1, 1, 2}, {0, 0, 1, 2}, {0, 1, 2, 2}, {0, 1, 2, 1}, {0, 1, 2, 1}}; // N - 2
	static int[][] dy4 = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0 ,0, 1}}; // N - 1
	
	static int[] dx5 = {0, 0, 1, 1}; // N - 1
	static int[] dy5 = {0, 1, 0, 1}; // N - 1
	
	static long block(int[][] map, int N) {
		long sum = Long.MIN_VALUE, len = 4;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 3; j++) {
				long tmp = 0;
				for (int d = 0; d < len; d++) 
					tmp += map[i + dx[d]][j + dy[d]];
				sum = Math.max(sum, tmp);
			}
		}
		
		for (int i = 0; i < N - 3; i++) {
			for (int j = 0; j < N; j++) {
				long tmp = 0;
				for (int d = 0; d < len; d++) 
					tmp += map[i + dx2[d]][j + dy2[d]];
				sum = Math.max(sum, tmp);
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				long tmp = 0;
				for (int d = 0; d < len; d++) 
					tmp += map[i + dx5[d]][j + dy5[d]];
				sum = Math.max(sum, tmp);
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 2; j++) {
				for (int d = 0; d < dx3.length; d++) {
					long tmp = 0;
					for (int d2 = 0; d2 < len; d2++) {
						tmp += map[i + dx3[d][d2]][j + dy3[d][d2]];
					}
					sum = Math.max(sum, tmp);
				}
			}
		}
		
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < N - 1; j++) {
				for (int d = 0; d < dx4.length; d++) {
					long tmp = 0;
					for (int d2 = 0; d2 < len; d2++) {
						tmp += map[i + dx4[d][d2]][j + dy4[d][d2]];
					}
					sum = Math.max(sum, tmp);
				}
			}
		}
		return sum;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine().trim());
        
        int tc = 1;
        while (N != 0) {
        	int[][] map = new int[N][N];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	long sum = block(map, N);
        	sb.append(tc++).append(". ").append(sum).append("\n");
        	N = Integer.parseInt(br.readLine().trim());
        }
        
        System.out.println(sb);
        br.close();
	}
}

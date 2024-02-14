import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static StringBuilder sb;
	
	static String divide(int N, int x, int y) {
		int first = map[x][y];
		
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (map[i][j] != first) {
					if (N == 2) {
						String temp = "";
						for (int k = x; k < x + N; k++) {
							for (int q = y; q < y + N; q++) {
								temp += map[k][q];
							}
						}
						return "(" + temp + ")";
					} else {
						String temp = "";
						temp += divide(N / 2, x, y);
						temp += divide(N / 2, x, y + N / 2);
						temp += divide(N / 2, x + N / 2, y);
						temp += divide(N / 2, x + N / 2, y + N / 2);
						return "(" + temp + ")";
					}
				}
			}
		}
		
		return first + "";
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
        	String str = br.readLine();
        	for (int j = 1, k = 0; j < N + 1; j++) {
        		map[i][j] = str.charAt(k++) - '0';
        	}
        }

        String temp = divide(N, 1, 1);
        sb.append(temp);

        System.out.println(sb);
        br.close();
	}
}
import java.io.*;
import java.util.*;

public class Main {
	static int white, blue;
	static int[][] map;
	static StringBuilder sb;
	
	static void divide(int N, int x, int y) {
		int first = map[x][y];
		
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (map[i][j] != first) {
					divide(N / 2, x, y);
					divide(N / 2, x, y + N / 2);
					divide(N / 2, x + N / 2, y);
					divide(N / 2, x + N / 2, y + N / 2);
					return;
				}
			}
		}
		if (first == 1) blue++;
		else white++;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 1; j < N + 1; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        white = 0;
        blue = 0;
        
        divide(N, 1, 1);

        sb.append(white).append("\n").append(blue);
        System.out.println(sb);
        br.close();
	}
}

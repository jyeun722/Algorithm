import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int result = 0;
        
        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[102][102];
        
        for (int n = 0; n < N; n++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	for (int i = x + 1; i < x + 11; i++) {
        		for (int j = y + 1; j < y + 11; j++) {
        			paper[i][j] = 1;
        		}
        	}
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        for (int i = 0; i < 102; i++) {
        	for (int j = 0; j < 102; j++) {
        		if (paper[i][j] == 0) {
        			for (int d = 0; d < dx.length; d++) {
            			int nextX = i + dx[d];
            			int nextY = j + dy[d];
            			if (nextX < 0 || nextY < 0 || nextX >= 102 || nextY >= 102) continue;
            			if (paper[nextX][nextY] == 1) result++;
            		}
        		}
        		
        	}
        }
        
        System.out.println(result);
        br.close();
	}
}

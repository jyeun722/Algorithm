import java.io.*;
import java.util.*;

public class Main {
	static int R, C, result;
	static char[][] board;
	static boolean[] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static void dfs(int x, int y, int len) {
		result = Math.max(result, len);
		
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
			
			int idx = board[nextX][nextY];
			if (visit[idx]) continue;
			visit[idx] = true;
			
			dfs(nextX, nextY, len + 1);
			visit[idx] = false;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = 1;
        
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < C; j++) {
        		board[i][j] = str.charAt(j);
        	}
        }
        
        visit = new boolean[150];
        visit[board[0][0]] = true;
        dfs(0, 0, 1);
         
        System.out.println(result);
        br.close();
	}
}

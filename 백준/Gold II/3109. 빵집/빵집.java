import java.io.*;
import java.util.*;

public class Main {
	static int R, C, result;
	static char[][] map;
	
	static int[] dx = {-1, 0, 1};
	
	static boolean dfs(int x, int y, char v) {
		map[x][y] = v;
		if (y == C - 1) {
			result++;
			return true;
		}

		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + 1;
			
			if (nextX < 0 || nextX >= R) continue;
			
			if (map[nextX][nextY] == '.') {
				boolean check = dfs(nextX, nextY, v);
				if (check) return true;
			}
		}
		return false;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < C; j++) {
        		map[i][j] = str.charAt(j);
        	}
        } 
        
        result = 0;
        for (int i = 0; i < R; i++) {
        	if (map[i][0] == '.') dfs(i, 0, '-');
        }
        
        System.out.println(result);
        br.close();
	}
}

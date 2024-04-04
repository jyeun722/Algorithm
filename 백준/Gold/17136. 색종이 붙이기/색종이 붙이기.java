import java.io.*;
import java.util.*;

public class Main {
	static int allCnt, result;
	static int[][] map;
	
	static boolean count(int plus, int i, int j) {
		for (int x = i; x < i + plus; x++) {
			for (int y = j; y < j + plus; y++) {
				if (x >= 10 || y >= 10) return false;
				if (map[x][y] == 0) return false;
			}
		}
		return true;
	}
	
	static void fill(int plus, int ele, int i, int j) {
		for (int x = i; x < i + plus; x++) {
			for (int y = j; y < j + plus; y++) {
				map[x][y] = ele;
			}
		}
	}
	
	static void dfs(int o, int tw, int th, int fo, int fi, int cnt, int paper) {
		if (paper >= result) return;
		if (cnt == 0) {
			result = Math.min(result, paper);
			return;
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					if (fi > 0) {
						int plus = 5;
						if (count(plus, i, j)) {
							fill(plus, 0, i, j);
							dfs(o, tw, th, fo, fi - 1, cnt - plus * plus, paper + 1);
							fill(plus, 1, i, j);
						}
					} 
					if (fo > 0) {
						int plus = 4;
						if (count(plus, i, j)) {
							fill(plus, 0, i, j);
							dfs(o, tw, th, fo - 1, fi, cnt - plus * plus, paper + 1);
							fill(plus, 1, i, j);
						}
					} 
					if (th > 0) {
						int plus = 3;
						if (count(plus, i, j)) {
							fill(plus, 0, i, j);
							dfs(o, tw, th - 1, fo, fi, cnt - plus * plus, paper + 1);
							fill(plus, 1, i, j);
						}
					} 
					if (tw > 0) {
						int plus = 2;
						if (count(plus, i, j)) {
							fill(plus, 0, i, j);
							dfs(o, tw - 1, th, fo, fi, cnt - plus * plus, paper + 1);
							fill(plus, 1, i, j);
						}
					} 
					if (o > 0) {
						map[i][j] = 0;
						dfs(o - 1, tw, th, fo, fi, cnt - 1, paper + 1);
						map[i][j] = 1;
					}
					return;
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        allCnt = 0;
        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 10; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if (map[i][j] == 1) allCnt++;
        	}
        }
        
        result = Integer.MAX_VALUE;
        dfs(5, 5, 5, 5, 5, allCnt, 0);
        result = result == Integer.MAX_VALUE ? -1 : result;
         
        System.out.println(result);
        br.close();
	}
}

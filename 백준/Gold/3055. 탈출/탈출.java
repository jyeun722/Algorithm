import java.io.*;
import java.util.*;

public class Main {
	static int R, C, result;
	static char[][] map; // .: 빈 곳, *:물, X:돌, D: 비버의굴, S: 고슴도치 위치
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static void bfs(int[] loc, List<int[]> water) {
		Deque<int[]> animal = new ArrayDeque<>();
		animal.offer(new int[] {loc[0], loc[1], 0});
		map[loc[0]][loc[1]] = '.';
		
		boolean[][] visit = new boolean[R][C];
		visit[loc[0]][loc[1]] = true;
		
		while (!animal.isEmpty()) {
			int size = water.size();
			for (int i = 0; i < size; i++) {
				int[] temp = water.get(i);
				
				int nextX, nextY;
				for (int d = 0; d < dx.length; d++) {
					nextX = temp[0] + dx[d];
					nextY = temp[1] + dy[d];
					
					if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
					if (map[nextX][nextY] == '.') {
						map[nextX][nextY] = '*';
						water.add(new int[] {nextX, nextY});
					}
				}
			}
			
			size = animal.size();
			for (int i = 0; i < size; i++) {
				int[] temp = animal.poll();
				
				int nextX, nextY;
				for (int d = 0; d < dx.length; d++) {
					nextX = temp[0] + dx[d];
					nextY = temp[1] + dy[d];
					
					if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
					if (visit[nextX][nextY]) continue;
					
					visit[nextX][nextY] = true;
					if (map[nextX][nextY] == 'D') {
						result = temp[2] + 1;
						return;
					}
					if (map[nextX][nextY] == '.') {
						animal.offer(new int[] {nextX, nextY, temp[2] + 1});
					}
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        List<int[]> water = new ArrayList<>();
        int[] loc = new int[2];
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < C; j++) {
        		map[i][j] = str.charAt(j);
        		if (map[i][j] == 'S') loc = new int[] {i, j};
        		else if (map[i][j]  == '*') water.add(new int[] {i, j});
        	}
        }
        
        result = Integer.MAX_VALUE;
        bfs(loc, water);
        
        if (result == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(result);
        br.close();
	}
}

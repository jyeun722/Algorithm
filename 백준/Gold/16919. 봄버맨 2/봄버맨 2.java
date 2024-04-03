import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void simulation(int[][] map, HashMap<Integer, String> status) {
		int time = 1;
		while (time < 6) {
			if (time % 2 == 0) { // 폭탄 설치
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (map[i][j] == -1) map[i][j] = time;
					}
				}
			}
			
			Queue<int[]> remove = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (time - map[i][j] == 3) {
						map[i][j] = -1;
						for (int d = 0; d < dx.length; d++) {
							int nextX = i + dx[d];
							int nextY = j + dy[d];
							
							if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
							remove.add(new int[] {nextX, nextY});
						}
					}
				}
			}
			
			while (!remove.isEmpty()) {
				int[] temp = remove.poll();
				map[temp[0]][temp[1]] = -1;
			}
			
	        StringBuilder sb2 = new StringBuilder();
	        for (int i = 0; i < R; i++) {
	        	for (int j = 0; j < C; j++) {
	        		char add = map[i][j] == -1 ? '.' : 'O';
	        		sb2.append(add);
	        	}
	        	sb2.append("\n");
	        }
	        String result = sb2.toString();
	        status.put(time, result);
	        time++;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        HashMap<Integer, String> status = new HashMap<>();
        
        int[][] map = new int[R][C]; // .: 빈칸, O:폭탄
        for (int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < C; j++) {
        		char c = str.charAt(j);
        		if (c == 'O') map[i][j] = 0;
        		else map[i][j] = -1;
        	}
        }
        simulation(map, status);
        
        if (N % 2 == 0) sb.append(status.get(2));
        else if (N == 1) sb.append(status.get(1));
        else if (N % 4 == 3) sb.append(status.get(3));
        else if (N % 4 == 1) sb.append(status.get(5));
        
        System.out.println(sb);
        br.close();
	}
}

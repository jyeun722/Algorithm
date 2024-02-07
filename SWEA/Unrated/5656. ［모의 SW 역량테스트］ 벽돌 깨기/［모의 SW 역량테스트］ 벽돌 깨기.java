import java.io.*;
import java.util.*;

// 남은 벽돌의 개수
public class Solution {
	static int N, W, H, result; // N번 공격, W: 열, H: 행
	static int[] attack;
	static int[][] game;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static void cleanGameMap(int[][] tempArea) {
		for (int i = 0; i < W; i++) { // 열
			ArrayDeque<Integer> que = new ArrayDeque<>();
			for (int j = H - 1; j >= 0; j--) { // 행
				if (tempArea[j][i] != 0) {
					que.offer(tempArea[j][i]);
					tempArea[j][i] = 0;
				}
			}
			
			int size = que.size();
			for (int j = H - 1; j > H - 1 - size; j--) {
				tempArea[j][i] = que.poll();
			}
		}
	}
	
	static int attack(int[][] tempArea, int col) {
		int cnt = 0;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) { // 맨처음 블록 세팅
			if (tempArea[i][col] != 0) {
				int[] temp = {i, col, tempArea[i][col]};
				que.offer(temp);
				break;
			}
		}
		
		while (!que.isEmpty()) {
			cnt++;
			int[] temp = que.poll();
			tempArea[temp[0]][temp[1]] = 0;
			for (int d = 0; d < dx.length; d++) {
				int nextX = temp[0];
				int nextY = temp[1];
				for (int c = 0; c < temp[2] - 1; c++) {
					nextX = nextX + dx[d];
					nextY = nextY + dy[d];
					
					if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue;
					
					if (tempArea[nextX][nextY] != 0) {
						que.offer(new int[] {nextX, nextY, tempArea[nextX][nextY]});
						tempArea[nextX][nextY] = 0;
					}
				}
			}
		}
		return cnt;
	}
	
	static void attackSetting() {
		int[][] tempArea = new int[H][W];
		for (int t = 0; t < H; t++) {
			tempArea[t] = Arrays.copyOf(game[t], W);
		}

        int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += attack(tempArea, attack[i]);
			cleanGameMap(tempArea);
		}
		result = Math.max(sum, result);
	}
	
	static void perm(int cnt) {
		if (cnt == N) {
			attackSetting();
			return;
		}
		for (int i = 0; i < W; i++) {
			attack[cnt] = i;
			perm(cnt + 1);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	H = Integer.parseInt(st.nextToken());
        	
        	int allCnt = 0;
        	attack = new int[N];
        	game = new int[H][W];
        	result = 0;
        	
        	for (int i = 0; i < H; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < W; j++) {
        			int num = Integer.parseInt(st.nextToken());
        			game[i][j] = num;
        			if (num != 0) allCnt++;
        		}
        	}
            
        	perm(0);
        	int answer = allCnt - result;
        	
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb);
        br.close();
	}
}

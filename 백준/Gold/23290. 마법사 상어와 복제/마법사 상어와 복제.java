import java.io.*;
import java.util.*;

public class Main {
	static class Fish {
		int x, y, dir;
		
		public Fish (int x, int y, int dir) {
			this.x = x; this.y = y; this.dir = dir;
		}
	}
	
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}; // ←0, ↖1, ↑2, ↗3, →4, ↘5, ↓6, ↙7
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int[] dx2 = {-1, 0, 1, 0}; // 상 좌 하 우
	static int[] dy2 = {0, -1, 0, 1};
	
	static int max, maxD[];
	static int[] s;
	static void comb(int[][] f, int[] shark, boolean[][] visit, int cnt) {
		if(cnt == 3) {
			int sum = 0;
			int nextX = shark[0], nextY = shark[1];
			visit = new boolean[4][4];
			for (int i = 0; i < 3; i++) {
				nextX += dx2[s[i]];
				nextY += dy2[s[i]];
				
				if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4) return;
				if (visit[nextX][nextY]) continue;
				visit[nextX][nextY] = true;
				
				sum += f[nextX][nextY];
			}
			
			if (max < sum) {
				max = sum;
				maxD = Arrays.copyOf(s, 3);
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			s[cnt] = i;
			comb(f, shark, visit, cnt + 1);
		}
	}
	
	static void sharkMove(ArrayList<Integer>[][] fishMap, int[][] smell, int[][] f, int[] shark) {
		max = -1;
		maxD = new int[3];
		boolean[][] visit = null;
		comb(f, shark, visit, 0);
		
		int nextX = shark[0], nextY = shark[1];
		for (int i = 0; i < 3; i++) { // 방향마다
			nextX += dx2[maxD[i]];
			nextY += dy2[maxD[i]]; 
			
			if (fishMap[nextX][nextY].size() > 0) {
				fishMap[nextX][nextY] = new ArrayList<>();
				smell[nextX][nextY] = 3;
			}
			
			shark[0] = nextX;
			shark[1] = nextY;
		}
	}
	
	static int[][] fishMove(ArrayList<Integer>[][] fishMap, List<Integer>[][] copy, int[][] smell, int[] shark) {
		int[][] f = new int[4][4]; // 물고기 위치
		ArrayList<Fish> add = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = fishMap[i][j].size() - 1; k >= 0;) {
					int dir = fishMap[i][j].get(k);
					int nextX = i + dx[dir];
					int nextY = j + dy[dir];
					
					boolean flag = false;
					if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4) {
						flag = true; // 범위 넘어가는 경우
					} else if (nextX == shark[0] && nextY == shark[1]) {
						flag = true; // 상어가 있는 경우
					} else if (smell[nextX][nextY] > 0) {
						flag = true; // 물고기의 냄새가 있는 경우
					}
					
					if (!flag) { // 정상 이동
						fishMap[i][j].remove(k);
						add.add(new Fish(nextX, nextY, dir));
						f[nextX][nextY]++;
						k--;
					} else { // 방향 변경
						fishMap[i][j].set(k, (dir + 7) % 8);
						
						// 이동할 곳이 없는 경우(처음으로 다시 돌아온 경우)
						if (fishMap[i][j].get(k) == copy[i][j].get(k)) {
							f[i][j]++; // 물고기 위치 체크해서 반환
							k--;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < add.size(); i++) {
			Fish fish = add.get(i);
			fishMap[fish.x][fish.y].add(fish.dir);
		}
		
		return f;
	}
	
	static void repeat(ArrayList<Integer>[][] fishMap, int[] shark, int S) {
		int cnt = 0;
		ArrayList<Integer>[][] copy = new ArrayList[4][4];
		int[][] smell = new int[4][4]; // 물고기 냄새 표시
		s = new int[3];
		
		while (cnt++ < S) {
			// 1. 복제 -> 5에 나타남
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					copy[i][j] = new ArrayList<>(fishMap[i][j]);
				}
			}
			
			// 2. 물고기 이동 (상어, 물고기 냄서 이동 불가), 반시계 회전해서 이동까지
			int[][] f = fishMove(fishMap, copy, smell, shark);
			
			// 3. 상어 3칸 이동 -> 사전 순(0: 상, 1: 좌, 2: 하, 3: 우)
			sharkMove(fishMap, smell, f, shark);
			
			// 4. 2번 전 물고기 냄새 사라짐
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (smell[i][j] > 0) smell[i][j]--;
				}
			}
			
			// 5. 1에서의 복제 완성 (1에서의 위치와 방향)
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					fishMap[i][j].addAll(copy[i][j]);
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 물고기의 수
        int S = Integer.parseInt(st.nextToken()); // 마법을 연습한 횟수
        
        ArrayList<Integer>[][] fishMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) fishMap[i][j] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken()) - 1;
        	int y = Integer.parseInt(st.nextToken()) - 1;
        	int d = Integer.parseInt(st.nextToken()) - 1;
        	
        	fishMap[x][y].add(d);
        }
        
        int[] shark = new int[2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) shark[i] = Integer.parseInt(st.nextToken()) - 1;
        
        repeat(fishMap, shark, S);
         
        int size = 0;
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) size += fishMap[i][j].size();
        }
        System.out.println(size);
        br.close();
	}
}

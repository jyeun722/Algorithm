import java.io.*;
import java.util.*;

public class Main {
	static int[] count;
	static int[] dx = {-1, 1, 0, 0}; // ↑, ↓, ←, →
	static int[] dy = {0, 0, -1, 1};
	
	static int[][] orderInit(int N) {
		int[][] number = new int[N][N];
		int startX = (N + 1) / 2 - 1, startY = (N + 1) / 2 - 2;
		int endX = (N + 1) / 2, endY = (N + 1) / 2;
		
		number[startX][startY] = 1;
		int num = 2;
		while (num < N * N - 1) {
			for (int i = startX + 1; i <= endX && i < N; i++) {
				number[i][startY] = num++;
			} // ↓
			startX--;
			for (int i = startY + 1; i <= endY && i < N; i++) {
				number[endX][i] = num++;
			} // →
			startY--;
			for (int i = endX - 1; i >= startX && i >= 0; i--) {
				number[i][endY] = num++;
			} // ↑
			endX++;
			for (int i = endY - 1; i >= startY && i >= 0; i--) {
				number[startX][i] = num++; 
			} // ←
			endY++;
		}
		
		int[][] order = new int[4][N / 2];
		for (int i = 0; i < 4; i++) {
			int nextX = N / 2, nextY = N / 2;
			for (int j = 0; j < N / 2; j++) {
				nextX += dx[i];
				nextY += dy[i];
				order[i][j] = number[nextX][nextY];
			}
		}
		
		return order;
	}
	
	static ArrayList<Integer> init(int[][] map, int N) {
		ArrayList<Integer> marble = new ArrayList<>();
		
		int startX = (N + 1) / 2 - 1, startY = (N + 1) / 2 - 2;
		int endX = (N + 1) / 2, endY = (N + 1) / 2;
		
		if (map[startX][startY] != 0) marble.add(map[startX][startY]);
		int cnt = 0;
		stop: while (cnt++ < N / 2) {
			for (int i = startX + 1; i <= endX && i < N; i++) {
				if (map[i][startY] == 0) break stop;
				marble.add(map[i][startY]);
			} // ↓
			startX--;
			for (int i = startY + 1; i <= endY && i < N; i++) {
				if (map[endX][i] == 0) break stop;
				marble.add(map[endX][i]);
			} // →
			startY--;
			for (int i = endX - 1; i >= startX && i >= 0; i--) {
				if (map[i][endY] == 0) break stop;
				marble.add(map[i][endY]);
			} // ↑
			endX++;
			for (int i = endY - 1; i >= startY && i >= 0; i--) {
				if (map[startX][i] == 0) break stop;
				marble.add(map[startX][i]);
			} // ←
			endY++;
		}
		
		return marble;
	}
	
	static void destroy(ArrayList<Integer> marble, int[][] order, int dir, int len, int N) {
		for (int i = len - 1; i >= 0; i--) {
			int idx = order[dir][i] - 1;
			if (marble.size() - 1 < idx) continue;
			marble.remove(idx);
		}
	}
	
	static boolean bomb(ArrayList<Integer> marble) {
		marble.add(-1);
		int before = -10, cnt = 0;
		boolean flag = false;
		for (int i = 0; i < marble.size(); i++) {
			if (marble.get(i) == before) {
				cnt++; // 이전이랑 같으면 개수 증가
			} else { 
				before = marble.get(i); // 새로운 이전으로 변경
				if (cnt >= 4) { // 폭팔
					flag = true; // 폭발 체크
					int remove = i - cnt; // 삭제할 첫째 인덱스
					i = remove; // 그 다음 인덱스 설정
					while (cnt-- > 0) {
						count[marble.get(remove)]++;
						marble.remove(remove);
					}
				} 
				cnt = 1; // 새로운 이전에 대한 개수 초기화
			}
		}
		marble.remove(marble.size() - 1);
		return flag;
	}
	
	static ArrayList<Integer> change(ArrayList<Integer> marble, int N) {
		ArrayList<Integer> newMarble = new ArrayList<>();
		marble.add(-1);
		int before = -10, cnt = 0;
		for (int i = 0; i < marble.size(); i++) {
			if (marble.get(i) == before) {
				cnt++;
			} else {  // 0 -10 이 앞에 들어가기 때문에 2 처리
				newMarble.add(cnt);
				newMarble.add(before);
				
				if (newMarble.size() >= N * N - 1 + 2) break;
				
				before = marble.get(i);
				cnt = 1;
			}
		}
//		System.out.println(newMarble);
		for (int i = 0; i < 2; i++) {
			newMarble.remove(0);
		}
		while (newMarble.size() > N * N - 1) {
			newMarble.remove(newMarble.size() - 1);
		}
		return newMarble;
	}
	
	static void repeat(int[][] map, int[] direc, int[] len, int N, int M) {
		int[][] order = orderInit(N); // 칸의 번호를 매긴 배열 반환 
		ArrayList<Integer> marble = init(map, N); // 번호대로 구슬을 담은 리스트 반환
		for (int m = 0; m < M; m++) {
			// 1. 마법 시전: d방향으로 l만큼 파괴 // 구슬은 자동으로 땡겨짐
			destroy(marble, order, direc[m], len[m], N);
			
			// 2. 4개이상 연속하는 구슬 폭팔 
			boolean result = true;
			while (result) {
				result = bomb(marble);
			}
			
			// 3. 구슬 변화: A=구슬의 개수, B=구슬 그룹의 개수
			marble = change(marble, N);
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
       	int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[] direc = new int[M];
        int[] len = new int[M];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	direc[i] = Integer.parseInt(st.nextToken()) - 1;
        	len[i] = Integer.parseInt(st.nextToken());
        }
        
        count = new int[4];
        repeat(map, direc, len, N, M);
        
        System.out.println(count[1] + count[2] * 2 + count[3] * 3);
        br.close();
	}
}


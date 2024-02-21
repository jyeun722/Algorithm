import java.io.*;
import java.util.*;

public class Main {
	static int K; // 자석을 회전시키는 횟수 1~20회
	static int[][] magnet; // 0: N극, 1: S극 // 4:8
	static int[][] rotation; // 1: 시계방향, -1: 반시계방향 // K:2
	// 하나의 자석이 1칸 회전될 때,
	// 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1칸 회전
	
	static void rotate(boolean[] visit, int num, int d) {
		int[] temp = new int[8];
		if (d == 1) { // 시계방향
			for (int i = 0; i < 8; i++) {
				int idx = (i + 7) % 8;
				temp[i] = magnet[num][idx];
			}
		} else { // 반시계방향
			for (int i = 0; i < 8; i++) {
				int idx = (i + 1) % 8;
				temp[i] = magnet[num][idx];
			}
		}
		
		if (num > 0 && !visit[num - 1] && magnet[num - 1][2] != magnet[num][6]) {
			visit[num - 1] = true;
			rotate(visit, num - 1, d * (-1));
		}
		if (num < 3 && !visit[num + 1] && magnet[num + 1][6] != magnet[num][2]) {
			visit[num + 1] = true;
			rotate(visit, num + 1, d * (-1));
		}
		
		magnet[num] = Arrays.copyOf(temp, 8);
	}
	
	static int rotateStart() {
		for (int k = 0; k < K; k++) {
			int[] rot = rotation[k];
			boolean[] visit = new boolean[4];
			visit[rot[0]] = true;
			rotate(visit, rot[0], rot[1]);
		}
		
		int sum = 0;
		for (int m = 0; m < 4; m++) {
			int NS = magnet[m][0];
			if (NS == 1) {
				sum += Math.pow(2, m);
			}
		}
		return sum;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        magnet = new int[4][8];
    	for (int i = 0; i < 4; i++) { // 1 ~ 4번 자석
    		String str = br.readLine();
    		for (int j = 0; j < 8; j++) { // 각각 8개 날의 자성 정보
    			magnet[i][j] = str.charAt(j) - '0';
    		}
    	}
    	
        K = Integer.parseInt(br.readLine());
        	
        rotation = new int[K][2];
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	rotation[i][0] = Integer.parseInt(st.nextToken()) - 1; // 자석의 번호
        	rotation[i][1] = Integer.parseInt(st.nextToken()); // 회전 방향
        }
        
        int result = rotateStart();
        System.out.println(result);
         
        br.close();
	}
}

/*
시계방향
0 <- 7
1 <- 0
2 <- 1
3 <- 2
4 <- 3
5 <- 4
6 <- 5
7 <- 6

반시계방향
0 <- 1
1 <- 2
2 <- 3
3 <- 4
4 <- 5
5 <- 6
6 <- 7
7 <- 0
*/
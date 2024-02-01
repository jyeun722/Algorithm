import java.io.*;
import java.util.*;

class Shark {
	@Override
	public String toString() {
		return "Shark [r=" + r + ", c=" + c + ", s=" + speed + ", d=" + direction + ", z=" + size + "]";
	}

	int r, c, speed, direction, size;

	public Shark(int r, int c, int speed, int direction, int size) {
		super();
		this.r = r;
		this.c = c;
		this.speed = speed;
		this.direction = direction;
		this.size = size;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
}

public class Main {
	static int R, C, M;
	static int result = 0;
	static Shark[] sharks;
	
	static void duplicationShark() {
		int[][] sharkMap = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(sharkMap[i], -1);
		}
		
		for (int i = 0; i < sharks.length; i++) {
			Shark s = sharks[i];
			if (s == null) continue;
			if (sharkMap[s.r][s.c] == -1) {
				sharkMap[s.r][s.c] = i;
			}
			else {
				int idx = sharkMap[s.r][s.c];
				Shark beforeS = sharks[idx];
				if (s.size > beforeS.size) {
					sharkMap[s.r][s.c] = i;
					sharks[idx] = null;
				} else {
					sharks[i] = null;
				}
			}
		}
	}
	
	// direction: 0(위), 1(아래), 2(오른쪽), 3(왼쪽)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int changeX(Shark s, int nextX) {
		if (nextX >= 0 && nextX < R) {
			s.setR(nextX);
		} else {
			int d = s.direction == 0 ? 1 : 0; // 상어 방향 체인지
			s.setDirection(d);
			if (nextX < 0) {
				s.setR(Math.abs(nextX));
				return Math.abs(nextX);
			} else if (nextX >= R) {
				s.setR(2 * R - nextX - 2);
				return 2 * R - nextX - 2;
			}
		}
		return nextX;
	}
	
	static int changeY(Shark s, int nextY) {
		if (nextY >= 0 && nextY < C) { 
			s.setC(nextY);
		} else {
			int d = s.direction == 2 ? 3 : 2;
			s.setDirection(d);
			if (nextY < 0) {
				s.setC(Math.abs(nextY));
				return Math.abs(nextY);
			} else if (nextY >= C) {
				s.setC(2 * C - nextY - 2);
				return 2 * C - nextY - 2;
			}
		}
		return nextY;
	}
	
	static void sharkMoving() { // 상어 이동
		for (Shark s : sharks) {
			if (s == null) continue;
			int nextX = s.r + s.speed * dx[s.direction];
			int nextY = s.c + s.speed * dy[s.direction];
			if (s.direction == 0 || s.direction == 1) { // 위 아래 이동
				int temp = changeX(s, nextX);
				while (temp < 0 || temp >= R) {
					temp = changeX(s, temp);
				}
			} else { // 왼 오른 이동
				int temp = changeY(s, nextY);
				while (temp < 0 || temp >= C) {
					temp = changeY(s, temp);
				}
			}
		}
		duplicationShark();
	}
	
	static void sharkFishing(int col) { // 낚시꾼 위치에 따라서 가장 가까운 상어 잡기
		int minRow = R;
		int minSharkIdx = -1;
		for (int i = 0; i < sharks.length; i++) {
			Shark s = sharks[i];
			if (s == null) continue;
			if (s.c == col && s.r < minRow) {
				minRow = s.r;
				minSharkIdx = i;
			}
		}
		if (minSharkIdx != -1) {
			result += sharks[minSharkIdx].size;
			sharks[minSharkIdx] = null;
		}
	}
	
	static void fishing() {
		for (int i = 0; i < C; i++) { // 낚시꾼의 열 이동
			sharkFishing(i); // 상어 잡기
			sharkMoving();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if (M == 0) {
			System.out.println(result);
			br.close();
			return;
		}
		
		sharks = new Shark[M]; 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Shark s = new Shark(
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
			sharks[i] = s;
		}

		fishing();
		
		System.out.println(result);
		br.close();
	}
}

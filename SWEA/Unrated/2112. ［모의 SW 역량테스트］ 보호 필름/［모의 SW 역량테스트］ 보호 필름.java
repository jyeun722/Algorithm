import java.io.*;
import java.util.*;

public class Solution {
	static int D, W, K, result;
	static int[][] film, temp;
	static int[] fix;

	// 모든 필름의 세로가 성능검사 통과하는지 검사
	static boolean check(int[][] arr) {
		for (int j = 0; j < W; j++) {
			int cnt = 1, feature = arr[0][j]; // 맨 처음 특성
			boolean correct = false; // 아직 성능검사 통과 안한 상태
			for (int i = 1; i < D; i++) {
				if (feature == arr[i][j]) { // 특성이 연속되면 카운트 증가
					if (++cnt >= K) { // K개 이상 특성이면 correct 바꾸고 멈춤
						correct = true;
						break;
					}
				} else { // 특성이 연속되지 않으면 카운트 리셋, 확인 특성 리셋
					cnt = 1;
					feature = arr[i][j];
				}
			}
			if (!correct)
				return false; // 하나의 열이라도 correct 되지 못하면 false 반환
		}
		return true; // 전체 다 통과하면 true 리턴
	}

	static void ABDfs(int i, int AB, int limit) {
		if (i == result - 1)
			return;
		
		int[] t = new int[W]; // 빈 배열에 fix[i] 행의 열 값 저장
		System.arraycopy(film[fix[i]], 0, t, 0, W);
		
		Arrays.fill(film[fix[i]], AB); // fix[i] 행의 열 값에 AB 값으로 특성 변경시키기

		if (check(film)) { // 특성이 잘 변화되면
			result = Math.min(result, i + 1); // result 업데이트
		} else if (i + 1 < limit) { // 성능검사 통과하지 못했는데 아직 변화시킬 수 있다면 AB 특성 부여
			ABDfs(i + 1, 1, limit);
			ABDfs(i + 1, 0, limit);
		}

		System.arraycopy(t, 0, film[fix[i]], 0, W); // 배열 되돌리기
	}

	static void comb(int cnt, int limit, int start) {
		if (cnt + 1 == result || cnt == limit) {
			ABDfs(0, 1, limit);
			ABDfs(0, 0, limit);
			return;
		}

		for (int i = start; i < D; i++) {
			fix[cnt] = i;
			comb(cnt + 1, limit, i + 1);
		}
	}

	static void start() {
		for (int c = 1; c < D; c++) {
			if (c >= result) break;
			fix = new int[c];
			
			comb(0, c, 0);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 보호 필름 두께
			W = Integer.parseInt(st.nextToken()); // 가로 크기
			K = Integer.parseInt(st.nextToken()); // 합격 기준
			result = D;

			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if (check(film) || K == 1) result = 0;
			else start();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
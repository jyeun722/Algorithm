import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static String nums;
	static HashSet<String> numSet; // 중복 제거
	
	static int find() { // K번째 큰 숫자 찾기
		ArrayList<Integer> list = new ArrayList<>();
		for (String str : numSet) { // 16진수를 10진수로 변환
			int num = Integer.parseInt(str, 16);
			list.add(num);
		}
        
		// 역순 정렬
		Collections.sort(list, Comparator.reverseOrder());
		return list.get(K - 1); // k - 1 번 인덱스 숫자 반환
	}

	static void addNum() { // 숫자 분해해서 더하기
		int len = N / 4; // 길이는 4분면이므로 나누기 4
		String str;
		for (int i = 0; i < N; i += len) {
			str = nums.substring(i, i + len);
			numSet.add(str);
		}
	}
    
	static void rotate() { // 회전 시키기
		addNum();
		for (int i = 0; i < N / 4; i++) { // 변의 길이만큼 회전
			nums = nums.charAt(N - 1) + nums.substring(0, N - 1); // 마지막 글자 + 그전까지
			addNum();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 숫자의 개수
			K = Integer.parseInt(st.nextToken()); // 크기 순서

			nums = br.readLine();
			numSet = new HashSet<>();
			
			rotate();
			int result = find();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}

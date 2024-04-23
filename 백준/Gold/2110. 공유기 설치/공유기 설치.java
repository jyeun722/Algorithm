import java.io.*;
import java.util.*;

public class Main {
	static int distance(int[] home, int minDis, int N) {
		// 최소 거리(minDis)보다 먼 집에 공유기 설치해보기
		int cnt = 1;
		int last = home[0];
		
		// 현재 탐색하는 위치(home[i])와 직전에 설치했던 집의 위치(last)간 거리가
		// 최소거리(minDis)보다 크거나 같으면 설치 개수(cnt) 늘려주고 마지막 설치 위치(last) 갱신
		for (int i = 1; i < N; i++) {
			if (home[i] - last >= minDis) {
				cnt++;
				last = home[i];
			}
		}
		
		return cnt;
	}
	
	static void binarySearch(int[] home, int N, int C) {
		// basic: 1(배열의 시작 위치), N(배열의 길이) => 1, home[N - 1] - home[0] + 1
		int start = 1, end = home[N - 1] - home[0] + 1, mid; // 중간값: 최소 거리!
		
		while (start < end) { // Upper Bound
			mid = (start + end) / 2;
			
			int cnt = distance(home, mid, N);
			if (cnt < C) { // 설치 가능한 공유기 개수가 C에 미치지 못하면
				end = mid; // 거리를 좁하기 위해 end 줄이기
			} else { // C보다 많이 설치되면
				start = mid + 1; // 거리를 벌리기
			}
		}
		
		// Upper Bound는 탐색 값을 초과하는 첫 번째 값을 가리킴
		System.out.println(start - 1);
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
        
        int[] home = new int[N];
        for (int i = 0; i < N; i++) {
        	home[i] = Integer.parseInt(br.readLine());
        }
        // 집을 오름차순으로 정렬
        Arrays.sort(home);
        
        // 이분 탐색 시작
        binarySearch(home, N, C);
        
        br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int N, d, k, c, result;
	// 회전 초밥 벨트에 놓인 접시의 수
    // 초밥의 가짓수
    // 연속해서 먹는 접시의 수
    // 쿠폰 번호
	static int[] sushi;
	
	static void count() {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < k; i++) {
			map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
		} // 처음 배열 세팅
		
		int remove = 0, add = k;
		for (int i = 0; i < N; i++) {
			remove = remove % N;
			map.put(sushi[remove], map.get(sushi[remove]) - 1);
			if (map.get(sushi[remove]) == 0) map.remove(sushi[remove]);
			
			add = add % N;
			map.put(sushi[add], map.getOrDefault(sushi[add], 0) + 1); // 윈도우 슬라이딩
			
			map.put(c, map.getOrDefault(c, 0) + 1);
			
			result = Math.max(result, map.size());
			
			if (map.get(c) == 1) map.remove(c);
			if (result == k + 1) break;
			
			remove++; add++;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        sushi = new int[N];
        for (int i = 0; i < N; i++) {
        	sushi[i] = Integer.parseInt(br.readLine()); // 초밥 종류
        }
        
        result = 0;
        count();
        
        System.out.println(result);
        br.close();
	}
}

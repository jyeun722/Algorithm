import java.io.*;
import java.util.*;

public class Main {
	static int K, N;
	static int[] line;
	
	static long cut(long mid) {
		long cnt = 0;
		for (int l : line) {
			cnt += l / mid;
		}
		return cnt; // 만들어지는 개수
	}
	
	static long binary(long start, long end) {
		long mid = -1; // 잘라낼 랜선 길이
		while (start <= end) {
			mid = (start + end) / 2;
			if (cut(mid) < N) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 랜선의 개수
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
        
        long end = 0;
        line = new int[K];
        for (int i = 0; i < K; i++) {
        	line[i] = Integer.parseInt(br.readLine());
        	end += line[i];
        }
        
        long result = binary(1, end);
        System.out.println(result);
        br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] tree;
	
	static long cut(long mid) {
		long sum = 0;
		for (int t : tree) {
			if (t > mid) sum += t - mid;
		}
		return sum;
	}
	
	static long binary(long start, long end) {
		long mid = -1; // 남길 나무 높이
		while (start <= end) {
			mid = (start + end) / 2;
			if (cut(mid) >= M) {
				start = mid + 1;
			} else if (cut(mid) < M) {
				end = mid - 1;
			} 
		}
		return end;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 나무의 수
        M = Integer.parseInt(st.nextToken()); // 원하는 나무의 길이
        
        long end = 0;
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	tree[i] = Integer.parseInt(st.nextToken());
        	end += tree[i];
        }
        
        long result = binary(0, end);
        System.out.println(result);
        br.close();
	}
}

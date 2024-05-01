import java.io.*;
import java.util.*;

public class Main {
	static long init(long[] nums, long[] tree, int start, int end, int node) {
		if (start == end) return tree[node] = nums[start];
		
		return tree[node] = init(nums, tree, start, (start + end) / 2, node * 2)
				* init(nums, tree, (start + end) / 2 + 1, end, node * 2 + 1) % 1_000_000_007;
	}
	
	static long update(long[] tree, int start, int end, int node, int b, long c) {
		if (b < start || b > end) return tree[node];
		
		if (start == end) return tree[node] = c;
		
		return tree[node] = update(tree, start, (start + end) / 2, node * 2, b, c)
				* update(tree, (start + end) / 2 + 1, end, node * 2 + 1, b, c) % 1_000_000_007;
	}
	
	static long mul(long[] tree, int start, int end, int left, int right, int node) {
		if (left > end || right < start) return 1;
		
		if (left <= start && right >= end) return tree[node];
		
		return mul(tree, start, (start + end) / 2, left, right, node * 2)
				* mul(tree, (start + end) / 2 + 1, end, left, right, node * 2 + 1) % 1_000_000_007;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수
        
        int hei = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, hei);
        long[] nums = new long[size + 1];
        long[] tree = new long[size + 1];
        
        for (int i = 1; i < N + 1; i++) {
        	nums[i] = Long.parseLong(br.readLine());
        }
        init(nums, tree, 1, N, 1);
        
        for (int i = 0; i < M + K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	if (a == 1) { // b 번째 숫자를 c로 변경
        		update(tree, 1, N, 1, b, c);
        	} else {
        		long result = mul(tree, 1, N, b, c, 1) % 1_000_000_007;
        		sb.append(result).append("\n");
        	} // b ~ c 까지의 곱
        	
        }
         
        System.out.println(sb);
        br.close();
	}
}

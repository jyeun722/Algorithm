import java.io.*;
import java.util.*;

public class Main {
	static long[] maxTree, minTree;
	
	static long maxInit(long[] number, int node, int start, int end) {
		if (start == end) return maxTree[node] = number[start];
		
		return maxTree[node] = Math.max(maxInit(number, node * 2, start, (start + end) / 2),
				maxInit(number, node * 2 + 1, (start + end) / 2 + 1, end));
	}
	
	static long minInit(long[] number, int node, int start, int end) {
		if (start == end) return minTree[node] = number[start];
		
		return minTree[node] = Math.min(minInit(number, node * 2, start, (start + end) / 2),
				minInit(number, node * 2 + 1, (start + end) / 2 + 1, end));
	}
	
	static long maxFind(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 0;
		
		if (start >= left && end <= right) {
			return maxTree[node];
		}
		 
		return Math.max(maxFind(node * 2, start, (start + end) / 2, left, right),
						maxFind(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
	
	static long minFind(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return Integer.MAX_VALUE;
		
		if (start >= left && end <= right) {
			return minTree[node];
		}
		
		return Math.min(minFind(node * 2, start, (start + end) / 2, left, right),
						minFind(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        long[] number = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
        	number[i] = Long.parseLong(br.readLine());
        }
        
        int hei = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, hei);
        maxTree = new long[size + 1];
        minTree = new long[size + 1];
        
        maxInit(number, 1, 1, N);
        minInit(number, 1, 1, N);
        
        long max, min;
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	max = maxFind(1, 1, N, a, b);
        	min = minFind(1, 1, N, a, b);
        	
        	sb.append(min).append(" ").append(max).append("\n");
        }
        
        System.out.println(sb);
        br.close();
	}
}

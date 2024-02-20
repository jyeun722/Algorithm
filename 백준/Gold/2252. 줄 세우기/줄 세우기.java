import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] indegree;
	static ArrayList<Integer>[] arr;
	
	static void topological(StringBuilder sb) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i]== 0) {
				que.offer(i);
			}
		}
		
		while (!que.isEmpty()) {
			int temp = que.poll();
			sb.append(temp + 1).append(" ");
			
			for (int v : arr[temp]) {
				indegree[v]--;
				
				if (indegree[v]== 0) {
					que.offer(v);
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 비교 횟수
        
        indegree = new int[N];
        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) arr[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int front = Integer.parseInt(st.nextToken()) - 1;
    		int end = Integer.parseInt(st.nextToken()) - 1;
    		
    		arr[front].add(end);
    		indegree[end]++; // end로 향하는 간선의 개수 증가
        }
        
        topological(sb);
        
        System.out.println(sb);
        br.close();
	}
}

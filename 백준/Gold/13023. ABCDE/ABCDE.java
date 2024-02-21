import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result;
	static List<Integer>[] arr;
	static boolean[] visit;
	
	static void dfs(int x, int cnt) {
		if (result == 1) return;
		if (cnt == 5) {
			result = 1;
			return;
		}
		for (int i = 0; i < arr[x].size(); i++) {
			int num = arr[x].get(i);
			if (visit[num]) continue;
			visit[num] = true;
			dfs(num, cnt + 1);
			visit[num] = false;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;
        
        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) arr[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	arr[a].add(b);
        	arr[b].add(a);
        }
        
        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
        	visit[i] = true;
        	dfs(i, 1);
        	visit[i] = false;
        }
        
        System.out.println(result);
        br.close();
	}
}

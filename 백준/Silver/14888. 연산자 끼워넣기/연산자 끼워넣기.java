import java.io.*;
import java.util.*;

public class Main {
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, N;
	static int[] arr, opers;
	
	static void dfs(int cnt, int sum) {
		if (cnt == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
		}
		
		if (opers[0] > 0) {
			opers[0]--;
			dfs(cnt + 1, sum + arr[cnt]);
			opers[0]++;
		}
		if (opers[1] > 0) {
			opers[1]--;
			dfs(cnt + 1, sum - arr[cnt]);
			opers[1]++;
		}
		if (opers[2] > 0) {
			opers[2]--;
			dfs(cnt + 1, sum * arr[cnt]);
			opers[2]++;
		}
		if (opers[3] > 0) {
			opers[3]--;
			dfs(cnt + 1, sum / arr[cnt]);
			opers[3]++;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
		
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        opers = new int[4]; // +, -, *, /
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) opers[i] = Integer.parseInt(st.nextToken());
        
        dfs(1, arr[0]);
        
        sb.append(max).append("\n").append(min);
        System.out.println(sb);
        br.close();
	}
}

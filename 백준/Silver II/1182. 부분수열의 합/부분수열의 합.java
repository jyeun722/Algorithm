import java.io.*;
import java.util.*;

public class Main {
	static int N, S, count;
	static int[] nums;
	static boolean[] visit;
	
	static void subs(int cnt, int sum, int num) {
		if (cnt == N ) {
			if (num != 0 && sum == S) count++;
			return;
		}
		visit[cnt] = true;
		subs(cnt + 1, sum + nums[cnt], num + 1);
		visit[cnt] = false;
		subs(cnt + 1, sum, num);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        count = 0;
        subs(0, 0, 0);
         
        System.out.println(count);
        br.close();
	}
}

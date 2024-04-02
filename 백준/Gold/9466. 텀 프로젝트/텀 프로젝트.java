import java.io.*;
import java.util.*;

public class Main {
	static int result;
	static int[] students;
	static boolean[] visit, check;
	
	static void dfs(int ver) {
		if (visit[ver]) { // 순회인 경우
			check[ver] = true;
			result++;
		} else visit[ver] = true;
			
		// 순회 1회째: 이전에 방문 안한 경우
		// 순회 2번째: 팀 결성인 애들을 체크 안한 경우 
		if (!check[students[ver]]) dfs(students[ver]);
		
		visit[ver] = false; // 순회할 때 마다 사용할거니까 원상복귀
		check[ver] = true; // 일단 방문했으니까 check 는 true
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
        	int n = Integer.parseInt(br.readLine());
        	
        	students = new int[n + 1];
        	st = new StringTokenizer(br.readLine());
        	for (int i = 1; i < n + 1; i++) {
        		students[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	visit = new boolean[n + 1]; // 순회마다 사용할 애 (초기: 전부 false)
        	check = new boolean[n + 1]; // 성공이든 실패이든 한번이라도 방문했으면 체크
        	
        	result = 0;
        	for (int i = 1; i < n + 1; i++) {
        		if (!check[i]) dfs(i); // 방문 안했던 애들만
        	}
        	
        	sb.append(n - result).append("\n");
        }
         
        System.out.println(sb);
        br.close();
	}
}

import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
 
    static int bfs(List<Integer>[] map) {
        Deque<Integer> que;
        boolean[] visit;
 
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            que = new ArrayDeque<>();
            que.offer(i);
 
            visit = new boolean[N];
            visit[i] = true;
 
            int result = 0, cnt = 0;
 
            EXIT_WHILE:
            while (!que.isEmpty()) {
                int size = que.size();
                cnt++;
 
                for (int j = 0; j < size; j++) {
                    int node = que.poll();
                    for (int next : map[node]) {
                        if (visit[next]) continue;
                        visit[next] = true;
                        result += cnt;
 
                        if (result >= answer) break EXIT_WHILE;
 
                        que.offer(next);
                    }
                }
            }
            answer = Math.min(result, answer);
        }
        return answer;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        long cur = System.currentTimeMillis();
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 사람 수
 
            List<Integer>[] map = new ArrayList[N];
            for (int i = 0; i < N; i++) map[i] = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        map[i].add(j);
                    }
                }
            }
 
            int answer = bfs(map);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
 
        long after = System.currentTimeMillis();
        System.out.println(after - cur);
 
 
        System.out.println(sb.toString());
        br.close();
    }
}
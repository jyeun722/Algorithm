import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, order, result;
    static List<Integer>[] list;
     
    static void bfs(int num) {
        boolean[] visit = new boolean[101];
        visit[num] = true;
         
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.offer(new int[] {num, 0}); // 숫자, 순서
         
        while (!que.isEmpty()) {
            int[] temp = que.poll();
             
            if (temp[1] > order) {
                order = temp[1];
                result = temp[0];
            }
            else if (temp[1] == order && result < temp[0]) result = temp[0]; 
             
            for (int i = 0; i < list[temp[0]].size(); i++) {
                int next = list[temp[0]].get(i);
                if (visit[next]) continue;
                visit[next] = true;
                que.offer(new int[] {next, temp[1] + 1});
            }
        }
                 
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        for (int tc = 1; tc < 11; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
            int M = Integer.parseInt(st.nextToken()); // 시작점
             
            list = new ArrayList[101];
            for (int i = 0; i < 101; i++) list[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                 
                list[from].add(to);
            }
             
            order = -1; result = -1;
            bfs(M);
 
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
 
        System.out.println(sb);
        br.close();
    }
}
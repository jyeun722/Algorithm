import java.io.*;
import java.util.*;
 
public class Solution {
    static void dfs(ArrayList<Integer>[] list, boolean[] visit) {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(0);
        visit[0] = true;
         
        while (!que.isEmpty()) {
            int ver = que.poll();
             
            for (int i = 0; i < list[ver].size(); i++) {
                int tmp = list[ver].get(i);
                if (!visit[tmp]) {
                    que.add(tmp);
                    visit[tmp] = true;
                }
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc < 11; tc++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
             
            ArrayList<Integer>[] list = new ArrayList[100];
            for (int i = 0; i < 100; i++) list[i] = new ArrayList<>();
             
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) {
                int ver = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                list[ver].add(dest);
            }
             
            boolean[] visit = new boolean[100];
             
            dfs(list, visit);
            int result = visit[99] ? 1 : 0;
 
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
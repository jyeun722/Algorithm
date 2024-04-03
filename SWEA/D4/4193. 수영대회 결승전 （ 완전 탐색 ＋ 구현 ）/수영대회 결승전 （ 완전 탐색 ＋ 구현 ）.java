import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, min;
    static int[][] map; // 0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
     
    static void bfs(int[] start, int[] end) {
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[] {start[0], start[1], 0});
         
        int[][] visit = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
        visit[start[0]][start[1]] = 0;
         
        while (!que.isEmpty()) {
            int[] temp = que.poll();
             
            if (temp[0] == end[0] && temp[1] == end[1]) {
                min = Math.min(min, temp[2]);
            }
             
            int nextX, nextY;
            for (int d = 0; d < dx.length; d++) {
                nextX = temp[0] + dx[d];
                nextY = temp[1] + dy[d];
                 
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                if (map[nextX][nextY] == 1) continue;
                 
                int time = temp[2] + 1;
                if (map[nextX][nextY] == 2) while (time == 0 || time % 3 != 0) time++;
                 
                if (visit[nextX][nextY] <= time) continue;
                visit[nextX][nextY] = time;
                 
                que.offer(new int[] {nextX, nextY, time});
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
             
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int[] start = new int[2], end = new int[2];
            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
             
            min = Integer.MAX_VALUE;
            bfs(start, end);
            if (min == Integer.MAX_VALUE) min = -1;
             
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, M, result;
    static char[][] map; // .: 빈 곳, *:악마, X:돌, D: 여신, S: 사람 위치
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
     
    static void bfs(int[] loc, List<int[]> water) {
        Deque<int[]> human = new ArrayDeque<>();
        human.offer(new int[] {loc[0], loc[1], 0});
        map[loc[0]][loc[1]] = '.';
         
        boolean[][] visit = new boolean[N][M];
        visit[loc[0]][loc[1]] = true;
         
        while (!human.isEmpty()) {
            int size = water.size();
            for (int i = 0; i < size; i++) {
                int[] temp = water.get(i);
                 
                int nextX, nextY;
                for (int d = 0; d < dx.length; d++) {
                    nextX = temp[0] + dx[d];
                    nextY = temp[1] + dy[d];
                     
                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                    if (map[nextX][nextY] == '.') {
                        map[nextX][nextY] = '*';
                        water.add(new int[] {nextX, nextY});
                    }
                }
            }
             
            size = human.size();
            for (int i = 0; i < size; i++) {
                int[] temp = human.poll();
                 
                int nextX, nextY;
                for (int d = 0; d < dx.length; d++) {
                    nextX = temp[0] + dx[d];
                    nextY = temp[1] + dy[d];
                     
                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                    if (visit[nextX][nextY]) continue;
                     
                    visit[nextX][nextY] = true;
                    if (map[nextX][nextY] == 'D') {
                        result = temp[2] + 1;
                        return;
                    }
                    if (map[nextX][nextY] == '.') {
                        human.offer(new int[] {nextX, nextY, temp[2] + 1});
                    }
                }
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            List<int[]> devil = new ArrayList<>();
            int[] loc = new int[2];
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'S') loc = new int[] {i, j};
                    else if (map[i][j]  == '*') devil.add(new int[] {i, j});
                }
            }
             
            result = Integer.MAX_VALUE;
            bfs(loc, devil);
             
            sb.append("#").append(tc).append(" ");
             
            if (result == Integer.MAX_VALUE) sb.append("GAME OVER");
            else sb.append(result);
             
            sb.append("\n");
        }
         
        System.out.println(sb.toString());
        br.close();
    }
}
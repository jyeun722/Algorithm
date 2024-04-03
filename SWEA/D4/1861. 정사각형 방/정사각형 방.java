import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, cnt;
    static int[][] room;
     
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
     
    static void dfs(int x, int y) {
        cnt++;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
             
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
             
            if (room[x][y] + 1 == room[nextX][nextY]) dfs(nextX, nextY);
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
             
            int roomNum = 0, maxCnt = 0;
             
            room = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            boolean[][] visit = new boolean[N][N];
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cnt = 0;
                    dfs(i, j);
                    if (cnt == maxCnt) roomNum = Math.min(roomNum, room[i][j]);
                    else if (cnt > maxCnt) {
                        maxCnt = cnt;
                        roomNum = room[i][j];
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(roomNum).append(" ").append(maxCnt).append("\n");
        }
         
        System.out.println(sb);
        br.close();
    }
}
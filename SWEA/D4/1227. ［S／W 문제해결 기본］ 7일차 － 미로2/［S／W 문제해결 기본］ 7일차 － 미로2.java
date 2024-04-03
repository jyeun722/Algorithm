import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] map;
    static int result = 0;
     
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
     
    static void dfs(boolean[][] visit, int x, int y) {
        if (map[x][y] == 3) {
            result = 1;
            return;
        }
         
        visit[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
             
            if (nextX < 0 || nextY < 0 || nextX >= 100 || nextY >= 100) continue;
             
            if (map[nextX][nextY] != 1 && !visit[nextX][nextY]) {
                dfs(visit, nextX, nextY);
            }
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        // 미로의 시작점은 (1, 1), 도착점은 (13, 13)
        // 1: 벽, 0: 길 // 1: 가능, 0: 불가능
        for (int tc = 0; tc < 10; tc++) {
            int tcNum = Integer.parseInt(br.readLine());
             
            result = 0;
             
            map = new int[100][100];
            for (int i = 0; i < 100; i++) {
                String str = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
             
            boolean[][] visit = new boolean[100][100];
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (map[i][j] == 2) {
                        dfs(visit, i, j);
                        break;
                    }
                }
            } 
             
            sb.append("#").append(tcNum).append(" ").append(result).append("\n");
        }
         
        System.out.println(sb);
        br.close();
    }
}
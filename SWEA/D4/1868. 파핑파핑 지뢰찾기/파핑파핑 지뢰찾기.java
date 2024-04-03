import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, result;
    static int[][] arr;
    static int[] dx = {0, 0, -1, -1, -1, 1, 1, 1};
    static int[] dy = {-1, 1, -1, 0, 1, -1, 0, 1};
     
    static boolean nearBomb(int x, int y) {
        for (int d = 0; d < dx.length; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];
             
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
             
            if (arr[nextX][nextY] == 1) return false; 
        }
        return true;
    }
     
    static void bfs(int x, int y) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.offer(new int[] {x, y});
         
        while (!que.isEmpty()) {
            int[] temp = que.poll();
             
            for (int d = 0; d < dx.length; d++) {
                int nextX = temp[0] + dx[d];
                int nextY = temp[1] + dy[d];
                 
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                 
                if (arr[nextX][nextY] == 0) {
                    result--;
                    arr[nextX][nextY] = 2;
                    if (nearBomb(nextX, nextY)) que.offer(new int[] {nextX, nextY}); 
                }
            }
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            result = 0;
             
            arr = new int[N][N]; 
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (str.charAt(j) == '*') arr[i][j]++;
                    else result++;
                }
            }
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 0 && nearBomb(i, j)) {
                        arr[i][j] = 2;
                        bfs(i, j);
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
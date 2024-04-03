import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, K, result;
    static int[][] map;
 
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
 
    static void dfs(boolean[][] visit, int x, int y, int len, boolean changeable) {
        int nextX, nextY;
        for (int d = 0; d < dx.length; d++) {
            nextX = x + dx[d];
            nextY = y + dy[d];
 
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
            if (visit[nextX][nextY]) continue;
            if (map[nextX][nextY] < map[x][y]) {
                visit[nextX][nextY] = true;
                dfs(visit, nextX, nextY, len + 1, changeable);
                visit[nextX][nextY] = false;
            } else {
                if (changeable) {
                    int depth = map[nextX][nextY] - map[x][y] + 1;
                    if (depth > K) {
                        result = Math.max(len, result);
                        continue;
                    }
 
                    map[nextX][nextY] -= depth;
                    visit[nextX][nextY] = true;
                    dfs(visit, nextX, nextY, len + 1, false);
 
                    visit[nextX][nextY] = false;
                    map[nextX][nextY] += depth;
                } else result = Math.max(len, result);
            }
        }
    }
 
    static void highStart(int high) {
        boolean[][] visit;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == high) {
                    visit = new boolean[N][N];
                    visit[i][j] = true;
                    dfs(visit, i, j, 1, true);
                }
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            result = 0;
 
            int high = 0;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    high = Math.max(high, num);
                }
            }
 
            highStart(high);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
import java.io.*;
import java.util.*;
 
public class Solution {
    static String[][] area;
    static HashSet<String> strs;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
 
    static void dfs(int x, int y, int cnt, String str) {
        if (cnt == 7) {
            strs.add(str);
            return;
        }
         
        int nextX, nextY;
        for (int d = 0; d < dx.length; d++) {
            nextX = x + dx[d];
            nextY = y + dy[d];
 
            if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4) continue;
 
            dfs(nextX, nextY, cnt + 1, str + area[nextX][nextY]);
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            area = new String[4][4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    area[i][j] = st.nextToken();
                }
            }
 
            strs = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 1, area[i][j]);
                }
            }
 
            sb.append("#").append(tc).append(" ").append(strs.size()).append("\n");
        }
 
        System.out.println(sb);
        br.close();
    }
}
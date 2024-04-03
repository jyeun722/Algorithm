import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, X, result;
    static boolean[] visit;
    static int[][] map;
     
    static void check() {
        for (int i = 0; i < N; i++) {
            int cur = map[i][0], len = 1; // 같은 숫자 길이
            boolean go = true;
            for (int j = 1; j < N; j++) {
                if (cur == map[i][j]) {
                    len++;
                    if (!go && len == X) {
                        len = 0;
                        go = true;
                    }
                } else if (cur + 1 == map[i][j]) { // 상승
                    if (len < X || !go) { // 이미 하강한 상태거나 길이가 작으면 check false return
                        go = false;
                        break; 
                    }
                    len = 1;
                } else if (cur - 1 == map[i][j]) { // 하강
                    if (!go) break;
                    go = false;
                    len = 1;
                } else {
                    go = false;
                    break;
                }
                cur = map[i][j];
            }
            if (go) result++;
        }
         
        for (int j = 0; j < N; j++) {
            int cur = map[0][j], len = 1; // 같은 숫자 길이
            boolean go = true;
            for (int i = 1; i < N; i++) {
                if (cur == map[i][j]) {
                    len++;
                    if (!go && len == X) {
                        len = 0;
                        go = true;
                    }
                } else if (cur + 1 == map[i][j]) { // 상승
                    if (len < X || !go) { // 이미 하강한 상태거나 길이가 작으면 check false return
                        go = false;
                        break; 
                    }
                    len = 1;
                } else if (cur - 1 == map[i][j]) { // 하강
                    if (!go) break;
                    go = false;
                    len = 1;
                } else {
                    go = false;
                    break;
                }
                cur = map[i][j];
            }
            if (go) result++;
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
            X = Integer.parseInt(st.nextToken());
            result = 0;
             
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            check();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
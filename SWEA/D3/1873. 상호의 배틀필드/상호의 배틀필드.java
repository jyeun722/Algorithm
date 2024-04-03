import java.io.*;
import java.util.*;
 
public class Solution {
    static int H, W, N, x, y;
    static String order;
    static char[][] map;
     
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
     
    static void game() {
        for (int i = 0; i < N; i++) {
            char or = order.charAt(i);
            int d;
            char temp = '~';
            if (or == 'U') {
                d = 0;
                temp = '^';
            }
            else if (or == 'D') {
                d = 1;
                temp = 'v';
            }
            else if (or == 'L') {
                d = 2;
                temp = '<';
            }
            else if (or == 'R') {
                d = 3;
                temp = '>';
            }
            else {
                if (map[x][y] == '^') d = 0;
                else if (map[x][y] == 'v') d = 1;
                else if (map[x][y] == '<') d = 2;
                else d = 3;
            }
             
            int nextX = x + dx[d];
            int nextY = y + dy[d];
             
            if (or == 'S') {
                boolean stone = false;
                while (true) {
                    if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) break;
                    else if (map[nextX][nextY] == '#') break;
                    else if (map[nextX][nextY] == '*') { 
                        stone = true;
                        break;
                    }
                    nextX += dx[d];
                    nextY += dy[d];
                }
         
                if (stone) map[nextX][nextY] = '.'; 
            } else {
                map[x][y] = temp;
                 
                if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue;
                 
                if (map[nextX][nextY] == '.') {
                    map[x][y] = '.';
                    x = nextX;
                    y = nextY;
                    map[x][y] = temp;
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
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
             
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    char temp = str.charAt(j);
                    map[i][j] = temp;
                    if ("^v<>".contains(temp + "")) {
                        x = i;
                        y = j;
                        temp = map[i][j];
                    }
                }
            }
             
             
             
            N = Integer.parseInt(br.readLine());
            order = br.readLine();
            game();
      
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
          
        System.out.println(sb);
        br.close();
    }
}
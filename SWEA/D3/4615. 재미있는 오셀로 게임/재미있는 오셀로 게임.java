import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 보드 한 변의 길이 (4, 6, 8)
            int M = Integer.parseInt(st.nextToken()); // 플에이어가 돌을 놓는 횟수
             
            int[][] map = new int[N][N];
            int half = N / 2, half2 = N / 2 - 1;
            map[half][half] = 2; map[half2][half2] = 2;
            map[half][half2] = 1; map[half2][half] = 1;
             
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int stone = Integer.parseInt(st.nextToken()); // 1: 흑, 2: 백
                 
                map[x][y] = stone;
                for (int d = 0; d < dx.length; d++) {
                    int nextX = x, nextY = y;
                    int finalX = -1, finalY = -1;
                    while (true) {
                        nextX += dx[d];
                        nextY += dy[d];
                        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) break;
                        if (map[nextX][nextY] == 0) break;
                         
                        if (map[nextX][nextY] == stone) {
                            finalX = nextX;
                            finalY = nextY;
                            break;
                        }
                    }
                     
                    if (finalX != -1) {
                        nextX = x; nextY = y;
                        while (true) {
                            nextX = nextX + dx[d];
                            nextY = nextY + dy[d];
                            if (nextX == finalX && nextY == finalY) break;
                            map[nextX][nextY] = stone;
                        }
                    }
                }
            }
             
            int white = 0, black = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) black++;
                    else if (map[i][j] == 2) white++;
                }
            }
             
            sb.append("#").append(tc).append(" ");
            sb.append(black).append(" ").append(white).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
import java.io.*;
 
public class Solution {
    static void snail(int[][] map, int N) {
        int startX = 0, endX = N, startY = 0, endY = N, number = 1;
        while (number < N * N + 1) {
            for (int i = startY; i < endY; i++) { // 왼쪽 -> 오른쪽 (가장 위 -> startX)
                map[startX][i] = number++;
            }
            startX++; // 맨 위에 다 채웠으니까 startX 행 증가
            for (int i = startX; i < endX; i++) { // 위 -> 아래 (가장 우측 -> endY)
                map[i][endY - 1] = number++;
            }
            endY--; // 가장 오른쪽 다 채웠으니까 endY 열 감소
            for (int i = endY - 1; i >= startY; i--) { // 오른쪽 -> 왼쪽 (가장 아래 -> endX)
                map[endX - 1][i] = number++;
            }
            endX--; // 맨 아래 다 채웠으니까 endX 행 감소
            for (int i = endX - 1; i >= startX; i--) { // 아래 -> 위 (가장 왼쪽 -> startY)
                map[i][startY] = number++;
            }
            startY++; // 가장 왼쪽 다 채웠으니까 startY 열 증가
        }
    }
     
    // StringBuilder에 달팽이 모드로 채운 숫자들 추가
    static void addSB(StringBuilder sb, int[][] map, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append("\n");
             
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
             
            snail(map, N);
            addSB(sb, map, N);
        }
        System.out.println(sb.toString());
    }
}
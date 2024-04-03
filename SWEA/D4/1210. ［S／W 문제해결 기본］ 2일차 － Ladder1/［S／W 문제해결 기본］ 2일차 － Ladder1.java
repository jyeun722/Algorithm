import java.io.*;
import java.util.*;
 
public class Solution {
    static final int SIZE = 100;
     
    // d => 0: 위, 1: 왼, 2: 오른
    static int find(int[][] ladder, int row, int col, int d) {
        while (row > 0) { // row가 0보다 크면 계속 이동
            if (d == 0) { // 위로 올라가는 중일 때
                if (col - 1 >= 0 && ladder[row][col - 1] == 1) { // 왼쪽 살피기
                    col--;
                    d = 1;
                } else if (col + 1 < SIZE && ladder[row][col + 1] == 1) { // 오른쪽 살피기
                    col++;
                    d = 2;
                } else row--; // 왼, 오 둘다 사다리 없으면 계속 위로 이동
            } else { // 왼, 오른쪽으로 이동 중 일 때
                if (row - 1 >= 0 && ladder[row - 1][col] == 1) { // 위쪽 살피기
                    row--;
                    d = 0;
                    continue;
                }
                 
                if (d == 1) col--; // 왼쪽으로 계속 이동
                else col++; // 오른쪽으로 계속 이동
            }
        }
        return col;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 0; tc < 10; tc++) {
            int tcN = Integer.parseInt(br.readLine());
            int[][] ladder = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int idx = -1;
            for (int i = 0; i < SIZE; i++) {
                if (ladder[SIZE - 1][i] == 2) {
                    idx = i;
                    break;
                }
            }
             
            int result = find(ladder, SIZE - 1, idx, 0);
            sb.append("#").append(tcN).append(" ").append(result).append("\n");
        }
         
        System.out.println(sb.toString());
        br.close();
    }
}
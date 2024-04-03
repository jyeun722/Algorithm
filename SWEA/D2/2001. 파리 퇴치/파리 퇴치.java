import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder(); 
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
             
            int result = 0;
            int[][] results = new int[N + 1][N + 1];
            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < N + 1; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    results[i][j] = results[i - 1][j] + results[i][j - 1] - results[i - 1][j - 1] + num;
                }
            }
             
            for (int i = M; i < N + 1; i++) {
                for (int j = M; j < N + 1; j++) {
                    int sum = results[i][j] - results[i - M][j] - results[i][j - M] + results[i - M][j - M];
                    if (sum > result) result = sum;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
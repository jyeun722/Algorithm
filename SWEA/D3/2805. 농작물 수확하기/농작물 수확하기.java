import java.io.*;
 
public class Solution {
    static int N;
    static int[][] ground;
     
    static int getCount() {
        int result = 0;
        for (int i = 0; i < N / 2 + 1; i++) {
            int idx = N / 2 - i;
            for (int j = 0; j < (i * 2) + 1; j++) {
                result += ground[i][idx++];
            }
        }
         
        for (int i = N - 1, row = 0; i > N / 2; i--) {
            int idx = N / 2 - row;
            for (int j = 0; j < (row * 2) + 1; j++) {
                result += ground[i][idx++];
            }
            row++;
        }
         
        return result;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            ground = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                String land = br.readLine();
                for (int j = 0; j < N; j++) {
                    ground[i][j] = land.charAt(j) - '0';
                }
            }
 
            int result = getCount();        
            sb.append("#" + tc + " " + result + "\n");
        }
         
        System.out.println(sb.toString());
    }
}
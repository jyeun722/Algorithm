import java.io.*;
import java.util.*;
 
public class Solution {
    static final int COUNT = 10;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            int result = 0;
            for (int i = 0; i < COUNT; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (num % 2 == 1) result += num;
            }
            sb.append("#" + tc + " " + result + "\n");
        }   
         
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
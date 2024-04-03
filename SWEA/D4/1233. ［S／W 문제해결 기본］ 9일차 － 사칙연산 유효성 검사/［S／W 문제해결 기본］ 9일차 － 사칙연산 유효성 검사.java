import java.io.*;
import java.util.*;
 
public class Solution {
    static int result = 1;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc < 11; tc++) {
            result = 1;
             
            int N = Integer.parseInt(br.readLine());
            if (N % 2 == 0) result = 0;
             
            int idx = 0;
            while (idx != N) {
                st = new StringTokenizer(br.readLine());
                 
                int cnt = st.countTokens();
                 
                idx = Integer.parseInt(st.nextToken());
                String node = st.nextToken();
                 
                if (cnt == 4) {
                    st.nextToken();
                    st.nextToken();
                    if ("*/+-".contains(node)) continue;
                    result = 0;
                } else {
                    if (!"*/+-".contains(node)) continue;
                    result = 0;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
import java.io.*;
         
public class Solution {
    static int memory(String str) {
        int cur = 1;
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) - '0' == cur) {
                result++;
                cur = cur == 1 ? 0 : 1;
            }
        }
        return result;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc < T + 1; tc++) {
            String str = br.readLine();
            int result = memory(str);
             
            sb.append("#" + tc + " " + result + "\n");
        }
         
        System.out.println(sb.toString());
    }
}
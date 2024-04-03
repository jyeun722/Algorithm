import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        for (int tc = 0; tc < 10; tc++) {
            int N = Integer.parseInt(br.readLine());
             
            int cnt = 0;
            st = new StringTokenizer(br.readLine());
            ArrayDeque<Integer> que = new ArrayDeque<>();
            for (int i = 1; i < 9; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 9) cnt++;
                que.add(num);
            }
             
            do {
                for (int i = 1; i < 6; i++) {
                    int num = que.pollFirst();
                    int afterNum = num - i;
                    if (num > 9 && afterNum < 10) cnt--;
                    if (afterNum < 0) afterNum = 0;
                    que.addLast(afterNum);
                     
                    if (cnt <= 0 && afterNum == 0) break;
                }
            } while (cnt >= 0 && que.peekLast() != 0);
             
            sb.append("#").append(N).append(" ");
            while (!que.isEmpty()) {
                int num = que.pollFirst();
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
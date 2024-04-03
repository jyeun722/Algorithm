import java.io.*;
import java.util.*;
 
public class Solution {
    static int dump(int[] box, int cnt) {
        for (int i = 0; i < cnt; i++) {
            Arrays.sort(box);
            box[0]++;
            box[box.length - 1]--;
        }
        Arrays.sort(box);
        return box[box.length - 1] - box[0];
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc < 11; tc++) {
            int dumpCnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
             
            int[] box = new int[100];
            for (int i = 0; i < 100; i++) box[i] = Integer.parseInt(st.nextToken());
             
            int result = dump(box, dumpCnt);
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
         
        System.out.println(sb.toString());
        br.close();
    }
}
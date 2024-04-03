import java.io.*;
import java.util.*;
 
public class Solution {
    static int[] nums, nums2;
    static boolean[] numVisit;
    static int result, all;
     
    static void dfs(int idx, int score) {
        if (idx == 9 && score > all - score) {
            result++;
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (numVisit[i]) continue;
             
            if (nums[idx] > nums2[i]) {
                numVisit[i] = true;
                dfs(idx + 1, score + nums[idx] + nums2[i]);
                numVisit[i] = false;
            } else {
                numVisit[i] = true;
                dfs(idx + 1, score);
                numVisit[i] = false;
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int every = 1;
        for (int i = 1; i < 10; i++) every *= i;
        all = 0;
        for (int i = 1; i < 19; i++) all += i;
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            nums = new int[9];
            boolean[] visit = new boolean[19];
             
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(st.nextToken());
                nums[i] = num;
                visit[num] = true;
            }
 
            nums2 = new int[9];
            for (int i = 1, j = 0; i < 19; i++) {
                if (visit[i]) continue;
                nums2[j++] = i;
            }
             
            numVisit = new boolean[9];
            result = 0; 
             
            dfs(0, 0);
             
            sb.append("#").append(tc).append(" ").append(result).append(" ").append(every - result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
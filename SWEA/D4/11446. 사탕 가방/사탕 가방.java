import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static long M, result;
    static long[] arr;
     
    static long bagCnt(long mid) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i] / mid;
        }
        return sum; // 들어있는 사탕 수
    }
     
    static void binarySearch(long end) {
        long start = 1;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (bagCnt(mid) >= M) {
                result = mid;
                start = mid + 1;
            }
            else end = mid - 1;
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());
            result = 0;
             
            long max = 0;
            st = new StringTokenizer(br.readLine());
            arr = new long[N];
            for (int i = 0; i < N; i++) {
                long num = Long.parseLong(st.nextToken());
                arr[i] = num;
                max = Math.max(max, num);
            }
             
            binarySearch(max);
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
          
        System.out.println(sb);
        br.close();
    }
}
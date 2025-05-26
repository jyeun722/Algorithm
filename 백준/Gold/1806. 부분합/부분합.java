import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 누적합 문제: 투포인터
        // 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N (10 ≤ N < 100,000)
        int S = Integer.parseInt(st.nextToken()); // S (0 < S ≤ 100,000,000)

        int[] nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, sum = 0;
        int len = Integer.MAX_VALUE;

        while (start <= end) {
            if (sum < S) {
                end++;
                if (end > N) break;
                sum += nums[end];
            } else if (sum >= S) {
                len = Math.min(len, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }
}
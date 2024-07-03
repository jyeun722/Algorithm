import java.io.*;
import java.util.*;

public class Main {
    // lower-bound: 크거나 값은 값을 반환
    static int binarySearch(int[] data, int n, int ans) {
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (ans <= data[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 자동차의 개수
        int q = Integer.parseInt(st.nextToken()); // 질의 개수

        int[] data = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);

        for (int i = 0; i < q; i++) {
            int expect = Integer.parseInt(br.readLine());
            int result = binarySearch(data, n, expect);

            if (result == 0 || result >= n - 1 || data[result] != expect) {
                // data에 없는 숫자일 경우, 첫번째인 경우, 마지막인 경우
                sb.append(0);
            } else {
                // 앞에 있을 수 있는 수의 경우의 수 * 뒤에 있을 수 있는 수의 경우의 수
                sb.append(result * (n - 1 - result));
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}

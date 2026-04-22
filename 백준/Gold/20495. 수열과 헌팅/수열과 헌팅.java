import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] originalL = new long[N];
        long[] originalR = new long[N];
        long[] sortedL = new long[N];
        long[] sortedR = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            
            originalL[i] = a - b;
            originalR[i] = a + b;
            sortedL[i] = a - b;
            sortedR[i] = a + b;
        }

        // 이분 탐색을 위해 L과 R 리스트를 각각 독립적으로 정렬
        Arrays.sort(sortedL);
        Arrays.sort(sortedR);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 1. 최솟값: R 배열에서 내 L보다 작은 값이 몇 개인지 찾기 (lowerBound)
            int minRank = lowerBound(sortedR, originalL[i]) + 1;
            
            // 2. 최댓값: L 배열에서 내 R보다 작거나 같은 값이 몇 개인지 찾기 (upperBound)
            int maxRank = upperBound(sortedL, originalR[i]);

            sb.append(minRank).append(" ").append(maxRank).append("\n");
        }

        System.out.print(sb);
    }

    // target보다 작은 값이 처음 나타나는 위치 (개수 반환)
    private static int lowerBound(long[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // target보다 큰 값이 처음 나타나는 위치 (개수 반환)
    private static int upperBound(long[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
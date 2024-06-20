import java.io.*;

public class Main {
    static long low;

    static void binarySearch(int K, int N) {
        low = 1;
        long high = K;

        // lower-bound
        while (low < high) {
            long mid = (low + high) / 2;  
            long count = 0;

            /*
             *  임의의 x에 대해 i번 째 행을 나눔으로써 x보다 작거나 같은 원소의 개수
             *  누적 합을 구한다.
             *  이 때 각 행의 원소의 개수가 N(열 개수)를 초과하지 않는 선에서 합해주어야 한다.
             */
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            // count가 많다는 것은 임의의 x(mid)보다 작은 수가 B[K]보다 많다는 뜻
            if (K <= count) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        binarySearch(k, N);
        System.out.println(low);

        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max = 0, size = 3;
    static int[] results = new int[size], card;

    static void comb(int cnt, int start) {
        if (cnt == size) {
            int sum = 0;
            for (int i = 0; i < size; i++) sum += results[i];
            if (max < sum && sum <= M) max = sum;
            return;
        }
        for (int i = start; i < N; i++) {
            results[cnt] = card[i];
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        card = new int[N];
        for (int i = 0; i < N; i++) card[i] = Integer.parseInt(st.nextToken());
        comb(0, 0);
        System.out.println(max);

        br.close();
    }
}
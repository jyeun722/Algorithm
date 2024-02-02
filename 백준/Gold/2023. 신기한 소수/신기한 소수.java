import java.io.*;

public class Main {
    static int N;
    static StringBuilder sb;

    static boolean prime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static void solve(int len, int num) {
        if (len == N) {
            sb.append(num).append("\n");
            return;
        }
        for (int i = 1; i < 10; i++) {
            int calNum = 10 * num + i;
            if (prime(calNum)) solve(len + 1, calNum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        solve(1, 2);
        solve(1, 3);
        solve(1, 5);
        solve(1, 7);

        System.out.println(sb);
        br.close();
    }
}
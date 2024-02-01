import java.io.*;

public class Main {
    static StringBuilder sb;

    static boolean prime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static void perm(int num, int cnt, int N) {
        if (cnt == N) {
            sb.append(num).append("\n");
            return;
        }
        for (int i = 1; i < 10; i++) {
            int calNum = 10 * num + i;
            if (prime(calNum)) perm(calNum, cnt + 1, N);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        perm(0, 0, N);

        System.out.println(sb);
        br.close();
    }
}
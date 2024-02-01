import java.io.*;
import java.util.*;

public class Main {
    static boolean prime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> allPrime(List<Integer> a) {
        List<Integer> b = new ArrayList<>();
        for (int n : a) if (prime(n)) b.add(n);
        return b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<Integer> before = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            if (prime(i)) before.add(i);
        }
        for (int cnt = 0; cnt < N - 1; cnt++) {
            List<Integer> after = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < before.size(); j++) {
                    int num = Integer.parseInt("" + before.get(j) + i);
                    after.add(num);
                }
            }
            before = allPrime(after);
        }

        Collections.sort(before);

        for (int i : before) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
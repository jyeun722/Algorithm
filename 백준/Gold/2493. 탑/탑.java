import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] results = new int[N];
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] > input) {
                    results[i] = stack.peek()[0] + 1;
                    break;
                }
                stack.pop();
            }
            stack.push(new int[]{i, input});
        }

        for (int i = 0; i < N; i++) {
            sb.append(results[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}

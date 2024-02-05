import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            nums.add(i);
        }

        int idx = k - 1;
        while (true) {
            int num = nums.remove(idx);
            sb.append(num + ", ");

            if (nums.size() == 0) break;
            idx = (idx + k - 1) % nums.size();
        }

        String result = "<" + sb.toString();
        result = result.substring(0, result.length() - 2) + ">";

        bw.append(result);
        bw.flush();
        bw.close();
    }
}
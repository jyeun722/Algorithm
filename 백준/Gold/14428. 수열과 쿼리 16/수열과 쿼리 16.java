import java.io.*;
import java.util.*;

public class Main {
    static int init(int[] tree, int[] arr, int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];

        return tree[node] = Math.min(init(tree, arr, node * 2, start, (start + end) / 2),
                init(tree, arr, node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static int min(int[] tree, int node, int start, int end, int left, int right) {
        if (left > end || right < start) return Integer.MAX_VALUE; // 범위 밖

        if (start >= left && end <= right) return tree[node]; // 범위 안

        return Math.min(min(tree, node * 2, start, (start + end) / 2, left, right),
                min(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right));
    }

    static int update(int[] tree, int start, int end, int node, int b, int c) {
        if (b < start || b > end) return tree[node]; // 범위 밖

        if (start == end) return tree[node] = c; // 범위 안

        return tree[node] = Math.min(update(tree, start, (start + end) / 2, node * 2, b, c),
                update(tree, (start + end) / 2 + 1, end, node * 2 + 1, b, c));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int hei = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, hei);
        int[] tree = new int[size + 1];

        init(tree, arr, 1, 1, N);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (q == 1) {
                update(tree, 1, N, 1, idx, v);
                arr[idx] = v;
            } else {
                int result = min(tree, 1, 1, N, idx, v);
                for (int j = idx; j < v + 1; j++) {
                    if (arr[j] == result) {
                        sb.append(j).append("\n");
                        break;
                    }
                }
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}

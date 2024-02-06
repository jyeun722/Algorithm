import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] arr;

    static void roll2(List<int[]> list) {
        int startX = 0, endX = N, startY = 0, endY = M;

        for (int[] temp : list) {
            int idx = R % temp.length;
            for (int i = startY; i < endY; i++) {
                arr[startX][i] = temp[idx++ % temp.length];
            }
            startX++;
            for (int i = startX; i < endX; i++) {
                arr[i][endY - 1] = temp[idx++ % temp.length];
            }
            endY--;
            for (int i = endY - 1; i >= startY; i--) {
                arr[endX - 1][i] = temp[idx++ % temp.length];
            }
            endX--;
            for (int i = endX - 1; i >= startX; i--) {
                arr[i][startY] = temp[idx++ % temp.length];
            }
            startY++;
        }
    }

    static void roll() {
        int startX = 0, endX = N, startY = 0, endY = M;
        int[] nums = new int[N * M];
        int idx = 0;
        int mid = Math.min(N, M) / 2;
        for (int k = 0; k < mid; k++) {
            for (int i = startY; i < endY; i++) {
                nums[idx++] = arr[startX][i];
            }
            startX++;
            for (int i = startX; i < endX; i++) {
                nums[idx++] = arr[i][endY - 1];
            }
            endY--;
            for (int i = endY - 1; i >= startY; i--) {
                nums[idx++] = arr[endX - 1][i];
            }
            endX--;
            for (int i = endX - 1; i >= startX; i--) {
                nums[idx++] = arr[i][startY];
            }
            startY++;
        }
        
        int n = N, m = M, start = 0, end = n * m - (n - 2) * (m - 2);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            int[] temp = Arrays.copyOfRange(nums, start, end);
            start = end;
            end = (n - 2 * (i + 1)) * (m - 2 * (i + 1)) - (n - 2 * (i + 2)) * (m - 2 * (i + 2)) + start;
            list.add(temp);
        }

        roll2(list);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        roll();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

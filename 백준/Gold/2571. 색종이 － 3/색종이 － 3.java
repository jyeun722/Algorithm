import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[100][100];

        int result = 1;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for (int i = r; i < r + 10; i++) {
                for (int j = c; j < c + 10; j++) {
                    arr[i][j] = 1;
                }
            }
        }

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 99; i++) {
                if (arr[i][j] != 0 && arr[i + 1][j] != 0) {
                    arr[i + 1][j] += arr[i][j];
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int h = 100;

                for (int k = j; k < 100; k++) {
                    h = Math.min(arr[i][k], h);
                    if (h == 0) break;
                    result = Math.max(result, h * (k - j + 1));

                }
            }
        }

        System.out.println(result);
        br.close();
    }
}
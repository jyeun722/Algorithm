import java.io.*;
import java.util.*;

public class Main {
    static int r, c, k, result = -1;
    static int[][] arr;

    static int cSorting(int row, int col) {
        int maxRow = 0;
        for (int i = 0; i < col; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < row; j++) {
                if (arr[j][i] == 0) continue;
                map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (int key : map.keySet()) {
                list.add(new int[]{key, map.get(key)});
            }

            Collections.sort(list, (i1, i2)
                    -> i1[1] == i2[1] ? i1[0] - i2[0] : i1[1] - i2[1]);

            for (int c = 0; c < 100; c++) {
                arr[c][i] = 0;
            }
            for (int c = 0, c2 = 0; c < list.size(); c++) {
                if (c2 >= 100) break;
                arr[c2++][i] = list.get(c)[0];
                arr[c2++][i] = list.get(c)[1];

                maxRow = Math.max(maxRow, c2);
            }
        }

        return maxRow;
    }

    static int rSorting(int row, int col) {
        int maxCol = 0;
        for (int i = 0; i < row; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (int key : map.keySet()) {
                list.add(new int[]{key, map.get(key)});
            }

            Collections.sort(list, (i1, i2)
                    -> i1[1] == i2[1] ? i1[0] - i2[0] : i1[1] - i2[1]);

            Arrays.fill(arr[i], 0);
            for (int c = 0, c2 = 0; c < list.size(); c++) {
                if (c2 >= 100) break;
                arr[i][c2++] = list.get(c)[0];
                arr[i][c2++] = list.get(c)[1];

                maxCol = Math.max(maxCol, c2);
            }
        }

        return maxCol;
    }

    static void sorting() {
        int row = 3;
        int col = 3;
        for (int i = 0; i <= 100; i++) {
            if (arr[r][c] == k) {
                result = i;
                break;
            }

            if (row >= col) {
                col = rSorting(row, col);
            } else {
                row = cSorting(row, col);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        // r: 모든 행에 대해서 정렬 (행 개수 >= 열 개수 일 때)
        // c: 모든 열에 대해서 정렬 (행 개수 < 열 개수 일 때)

        arr = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sorting();

        System.out.println(result);
        br.close();
    }
}
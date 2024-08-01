import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;

    static int visitCheck(boolean[] visit, int x, int y) {
        // 행
        for (int i = 0; i < 9; i++) {
            visit[map[i][y]] = true;
        }
        // 열
        for (int i = 0; i < 9; i++) {
            visit[map[x][i]] = true;
        }
        // 정사각형
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (i >= 9 || j >= 9) continue;
                visit[map[i][j]] = true;
            }
        }

        int cnt = 0;
        for (int i = 1; i < 10; i++) {
            if (!visit[i])
                cnt++;
        }
        return cnt;
    }

    static boolean back() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) {
                    boolean[] visit = new boolean[10];
                    int cnt = visitCheck(visit, i, j);
                    for (int v = 1; v < 11; v++) {
                        if (v == 10) {
                            map[i][j] = 0;
                            return false;
                        }
                        if (!visit[v]) {
                            map[i][j] = v;
                            boolean result = back();
                            if (result) break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
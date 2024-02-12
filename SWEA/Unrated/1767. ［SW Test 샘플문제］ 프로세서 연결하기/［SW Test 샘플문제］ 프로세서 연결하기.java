import java.io.*;
import java.util.*;

public class Solution {
    static int N, SIZE, result, count;
    static int[][] area;

    static ArrayList<int[]> cells;
    static int[] order;
    static boolean[][] ableDfs;

    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static int bfs(boolean[][] visitArea, int i) {
        int dis = Integer.MAX_VALUE, minD = -1;
        int x = cells.get(order[i])[0], y = cells.get(order[i])[1];
        for (int d = 0; d < dx.length; d++) {
            if (!ableDfs[order[i]][d]) continue;

            int nextX = x;
            int nextY = y;
            int line = 0;

            boolean die = false; // false: 충전선에 닿았으므로 죽지 않음(초기 상태)

            while (true) {
                nextX += dx[d];
                nextY += dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) break;

                if (area[nextX][nextY] == 1 || visitArea[nextX][nextY]) { // 다른 선이나 셀에 닿음
                    die = true; // true: 비정상적으로 죽음
                    break;
                }

                line++;

                if (line > dis) {
                    die = true;
                    break;
                }
            }
            if (!die && line < dis) { // 충전되었고 연결된 선이 작았을 경우
                dis = line;
                minD = d;
            }
        }
        while (minD != -1) { // 제일 작은 선으로 방문
            x += dx[minD];
            y += dy[minD];

            if (x < 0 || y < 0 || x >= N || y >= N) break;

            visitArea[x][y] = true;
        }
        return dis;
    }

    static void comb(int cnt, int start) {
        if (cnt == 1) {
            int sum = 0, cellCnt = 0;
            boolean[][] visitArea = new boolean[N][N];

            int idx = 1;
            for (int i = 0; i < SIZE; i++) {
                if (order[0] != i) order[idx++] = i;
            }

            for (int i = 0; i < SIZE; i++) {
                int num = bfs(visitArea, i);
                if (num != Integer.MAX_VALUE) {
                    sum += num; // 총 선 길이 확인
                    cellCnt++; // 총 연결된 셀 수 확인
                }

                if (SIZE - i - 1 + cellCnt < count) return;
            }

            if (cellCnt > count) { // 더 많이 충전되면 변경
                count = cellCnt;
                result = sum;
            } else if (cellCnt == count && sum < result) { // 더 선이 짧은 경우 변경
                result = sum;
            }
            return;
        }
        for (int i = start; i < SIZE; i++) {
            order[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            result = Integer.MAX_VALUE;
            count = 0;

            cells = new ArrayList<>();

            area = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) { // 0: cell, 1: core
                    int num = Integer.parseInt(st.nextToken());
                    area[i][j] = num;
                    if (num == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; // 이미 충전된 경우 패스
                        cells.add(new int[]{i, j});
                    }
                }
            }

            int idx = 0;
            SIZE = cells.size();
            ableDfs = new boolean[SIZE][4];

            // 우, 하, 좌, 상
            for (int i = 0; i < cells.size(); i++) {
                int cnt = 4;
                boolean[] direc = new boolean[4];
                for (int j = 0; j < 4; j++) direc[j] = true;
                int x = cells.get(i)[0];
                int y = cells.get(i)[1];
                for (int row = 0; row < x; row++) { // 위에 확인
                    if (area[row][y] == 1) {
                        direc[3] = false;
                        cnt--;
                        break;
                    }
                }
                for (int row = x + 1; row < N; row++) { // 아래 확인
                    if (area[row][y] == 1) {
                        direc[1] = false;
                        cnt--;
                        break;
                    }
                }
                for (int col = 0; col < y; col++) { // 왼쪽 확인
                    if (area[x][col] == 1) {
                        direc[2] = false;
                        cnt--;
                        break;
                    }
                }
                for (int col = y + 1; col < N; col++) { // 오른쪽 확인
                    if (area[x][col] == 1) {
                        direc[0] = false;
                        cnt--;
                        break;
                    }
                }
                if (cnt == 0) cells.remove(i--);
                else ableDfs[idx++] = direc;
            }

            SIZE = cells.size();
            order = new int[SIZE];

            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
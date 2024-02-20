import java.io.*;
import java.util.*;

public class Solution {
    static int N, result;
    static int[] first;
    static int[][] dessert;

    static int[] dx = {1, 1, -1, -1}; // (오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위)
    static int[] dy = {1, -1, -1, 1};

    static void dfs(int x, int y, boolean[] visit, int d, int cnt) {
        int nextX = x + dx[d]; // 다음 좌표
        int nextY = y + dy[d];
        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) return; // 범위 체크

        if (visit[dessert[nextX][nextY]]) { // 이미 방문한 디저트일 때
            if (nextX == first[0] && nextY == first[1]) { // 첫 시작점이면(한바퀴 돌고 온 것일 때, result 갱신)
                result = Math.max(result, cnt);
            }
            return; // 중복 디저트일 때 리턴
        }

        visit[dessert[nextX][nextY]] = true; // 방문 안한 디저트면 방문 처리
        if (d + 1 < 4) { // d가 더 증가 할 수 있으면
            dfs(nextX, nextY, visit, d + 1, cnt + 1); // 방향을 꺾기 위해 d를 증가해서 재귀
        }
        dfs(nextX, nextY, visit, d, cnt + 1); // 방향 꺾지않고 일직선 직진
        visit[dessert[nextX][nextY]] = false; // 방문 처리 해제
    }

    static void startPoint() {
        for (int i = 0; i < N - 2; i++) { // 마지막에서 두번째 행까지에서는 사각형 만들 수 없음
            for (int j = 1; j < N - 1; j++) { // 맨 앞, 뒤 열에서는 사각형 만들 수 없음
                boolean[] visit = new boolean[101]; // 디저트 종류의 수
                visit[dessert[i][j]] = true; // 디저트 시작점 방문 처리

                first = new int[]{i, j}; // 디저트 시작점 행, 열 좌표 저장
                dfs(i, j, visit, 0, 1); // x, y, visit, d(오른쪽 아래부터 시작), cnt
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            result = -1; // 디저트 못먹으면 -1

            dessert = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    dessert[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            startPoint();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
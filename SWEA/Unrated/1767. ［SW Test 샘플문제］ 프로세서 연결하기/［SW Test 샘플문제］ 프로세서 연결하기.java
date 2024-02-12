import java.io.*;
import java.util.*;

public class Solution {
    static int N, SIZE, result, count;
    static int[][] area;

    static ArrayList<int[]> cores;
    static int[] order;
    static boolean[][] ableDfs;

    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static int bfs(boolean[][] visitArea, int i) {
        int dis = Integer.MAX_VALUE, minD = -1; // dis: 최소 거리 저장, minD: 최소 거리의 방향 저장
        int x = cores.get(order[i])[0], y = cores.get(order[i])[1];
        for (int d = 0; d < dx.length; d++) {
            if (!ableDfs[order[i]][d]) continue; // 방문할 필요 없는 방향은 pass

            int nextX = x;
            int nextY = y;
            int line = 0;

            boolean die = false; // false: 충전선에 닿았으므로 죽지 않음(초기 상태)

            while (true) {
                nextX += dx[d];
                nextY += dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) break;

                if (visitArea[nextX][nextY]) { // 다른 선에 닿음
                    die = true; // true: 비정상적으로 죽음(충전 안됨!)
                    break;
                }

                line++; // 빈칸 인 경우 선 길이 추가

                if (line > dis) { // 탐색 중 이미 탐색한 다른 방향의 길이보다 커지면 중단
                    die = true; // 충전 안됨!
                    break;
                }
            }
            if (!die && line < dis) { // 충전되었고 연결된 선이 작았을 경우
                dis = line;
                minD = d;
            }
        }
        while (minD != -1) { // 제일 작은 선으로 방문 처리
            x += dx[minD];
            y += dy[minD];

            if (x < 0 || y < 0 || x >= N || y >= N) break;

            visitArea[x][y] = true;
        }
        return dis;
    }

    static void comb(int cnt, int start) {
        if (cnt == 1) { // 맨 앞에만 순서대로 하나씩 정하고
            int sum = 0, cellCnt = 0; // sum: 최소 선, cellCnt: 최대 core 개수
            boolean[][] visitArea = new boolean[N][N]; // 각 순서마다 선을 그을 배열

            int idx = 1;
            for (int i = 0; i < SIZE; i++) {
                if (order[0] != i) order[idx++] = i;
            } // 나머지는 그냥 순서대로 정렬

            for (int i = 0; i < SIZE; i++) { // 탐색가능한 제일 짧은 선 길이 저장
                int num = bfs(visitArea, i);
                if (num != Integer.MAX_VALUE) {
                    sum += num; // 총 선 길이 확인
                    cellCnt++; // 총 연결된 셀 수 확인
                }

                if (SIZE - i - 1 + cellCnt < count) return;
                // 현재 연결된 수 + 남은 수가 지금까지 연결된 core 수보다 작으면 탐색 중지
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

            cores = new ArrayList<>();
            area = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) { // 0: cell, 1: core
                    int num = Integer.parseInt(st.nextToken());
                    area[i][j] = num; // area에 core의 위치 저장
                    if (num == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; // 이미 충전된 경우 패스
                        cores.add(new int[]{i, j}); // 충전시킬 core 목록 추가
                    }
                }
            }

            int idx = 0;
            SIZE = cores.size(); // core 리스트 크기
            ableDfs = new boolean[SIZE][4]; // 코어에 따라서 우, 하, 좌, 상 검색할건지 boolean 배열 생성

            // 우, 하, 좌, 상
            for (int i = 0; i < cores.size(); i++) {
                int cnt = 4;
                boolean[] direc = new boolean[4]; // ableDfs에 들어갈 각 4방탐색 여부
                for (int j = 0; j < 4; j++) direc[j] = true; // 처음엔 다 true로 세팅
                int x = cores.get(i)[0];
                int y = cores.get(i)[1];
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
                } // 4방 탐색 돌면서 불가능한 방향은 false로 세팅
                
                if (cnt == 0) cores.remove(i--);  // 4방탐색 전부 불가능하면 core 리스트에서 제거
                else ableDfs[idx++] = direc;
            }

            SIZE = cores.size(); // 새롭게 core 크기 생성
            order = new int[SIZE]; // core의 탐색 순서를 저장할 배열

            comb(0, 0);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
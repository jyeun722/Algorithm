import java.io.*;
import java.util.*;

// 남은 벽돌의 개수
public class Solution {
    static int N, W, H, result; // N번 공격, W: 열, H: 행
    static int[] attack;
    static int[][] game;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    // 벽돌이 깨지고 깨진 공간으로 위에 있는 벽돌이 내려오도록 정리해주는 함수
    static void cleanGameMap(int[][] tempArea) {
        for (int i = 0; i < W; i++) { // 열
            ArrayDeque<Integer> que = new ArrayDeque<>(); // 열마다 남아있는 벽돌 더해줄 큐
            for (int j = H - 1; j >= 0; j--) { // 행
                if (tempArea[j][i] != 0) { // 벽돌이면 넣어주기
                    que.offer(tempArea[j][i]);
                    tempArea[j][i] = 0;
                }
            }

            int size = que.size();
            for (int j = H - 1; j > H - 1 - size; j--) {
                tempArea[j][i] = que.poll();
            } // 남아있는 벽돌을 아래서부터 순서대로 다시 넣어주기
        }
    }

    // col 변수 위치에서 구슬이 공격하는 함수
    static int attack(int[][] tempArea, int col) {
        int cnt = 0;
        ArrayDeque<int[]> que = new ArrayDeque<>(); // 깨진 벽돌 저장할 큐

        // 맨 처음 col 위치에서 구슬이 던져졌을 때 깨지는 첫 벽돌을 저장
        for (int i = 0; i < H; i++) {
            if (tempArea[i][col] != 0) {
                int[] temp = {i, col, tempArea[i][col]};
                que.offer(temp);
                break;
            }
        }

        // 큐가 비지 않았으면 지속적으로 벽돌 깨지는 중
        while (!que.isEmpty()) {
            cnt++; // 큐에 들어오는 것마다 깨지는 것이므로 카운트 증가
            int[] temp = que.poll();
            tempArea[temp[0]][temp[1]] = 0; // 깨진 공간 0으로 세팅
            for (int d = 0; d < dx.length; d++) { // 사방 탐색을 시작하는데
                int nextX = temp[0];
                int nextY = temp[1];
                for (int c = 0; c < temp[2] - 1; c++) { // 벽돌이 가지고 있는 숫자만큼 길이로
                    nextX = nextX + dx[d];
                    nextY = nextY + dy[d];

                    // 범위 체크
                    if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue;

                    // 0이 아니면(벽돌이 있는 상태면) 0으로 바꿔주고, 깨진 벽돌 리스트에 추가
                    if (tempArea[nextX][nextY] != 0) {
                        que.offer(new int[]{nextX, nextY, tempArea[nextX][nextY]});
                        tempArea[nextX][nextY] = 0;
                    }
                }
            }
        }
        return cnt;
    }

    // 환경 세팅: 기존 게임판을 복사해서 거기서 각각 공격자 공격 시작
    static void attackSetting() {
        int[][] tempArea = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                tempArea[i][j] = game[i][j];
            }
        } // 공간 복사

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += attack(tempArea, attack[i]); // 공격자마다 공격하고 깨진 수 합산
            cleanGameMap(tempArea); // 공격하고 맵 정리하기 (깨진 공간 내려서 채우기)
        }
        result = Math.max(sum, result); // 최댓값 계산
    }

    static void perm(int cnt) { // 중복 순열 -> visit 체크하지 않으면 중복 순열
        if (cnt == N) {
            attackSetting(); // 공격자 리스트 생성되면 공격하러 출발
            return;
        }
        for (int i = 0; i < W; i++) {
            attack[cnt] = i;
            perm(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // N번 구슬 쏘기
            W = Integer.parseInt(st.nextToken()); // 열
            H = Integer.parseInt(st.nextToken()); // 행

            int allCnt = 0;
            attack = new int[N]; // 공격할 구슬이 들어갈 배열
            game = new int[H][W]; // 게임판 구성
            result = 0; // 깨뜨린 벽돌 수

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    game[i][j] = num;
                    if (num != 0) allCnt++; // 전체 벽돌 수
                }
            }

            perm(0);
            int answer = allCnt - result; // 전체 벽돌 수 - 깨뜨린 벽돌 수

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

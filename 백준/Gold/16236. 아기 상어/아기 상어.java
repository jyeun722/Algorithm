import java.io.*;
import java.util.*;

public class Main {
    static int N, result, eatCount;
    static int[] shark;
    static int[][] area;
    static List<int[]> fish;

    static class Shark implements Comparable<Shark> {
        int x;
        int y;
        int dis;

        public Shark(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Shark o) {
            return this.dis < o.dis ? -1 : 1;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    static int move(int x, int y) {
        int sharkX = shark[0], sharkY = shark[1];
        boolean[][] visit = new boolean[N][N];

        PriorityQueue<Shark> que = new PriorityQueue<>();
        que.offer(new Shark(sharkX, sharkY, 0));
        visit[sharkX][sharkY] = true;

        while (!que.isEmpty()) {
            Shark s = que.poll();
            int sx = s.x;
            int sy = s.y;
            if (sx == x && sy == y) { // 물고기 잡았을 때
                return s.dis; // 총 이동거리 반환
            }

            int nextX, nextY;
            for (int d = 0; d < dx.length; d++) {
                nextX = sx + dx[d];
                nextY = sy + dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                if (visit[nextX][nextY] || area[nextX][nextY] > shark[2]) continue;
                visit[nextX][nextY] = true;
                que.offer(new Shark(nextX, nextY, s.dis + 1));
            }
        }
        return Integer.MAX_VALUE;
    }

    static void fishOrder() { // 물고기 순서 정해서 잡을건데, 가는길에 다른 애 있으면 돌아가기
        while (!fish.isEmpty()) {
            int minDis = Integer.MAX_VALUE;
            int row = -1;
            int col = -1;
            int minIdx = -1;

            for (int i = 0; i < fish.size(); i++) {
                int[] f = fish.get(i);

                if (f[2] >= shark[2] || Math.abs(shark[0] - f[0]) + Math.abs(shark[1] - f[1]) > minDis) continue;

                int dis = move(f[0], f[1]);
                if (dis < minDis) {
                    minDis = dis;
                    minIdx = i;
                    row = f[0];
                    col = f[1];
                } else if (dis == minDis) {
                    if (f[0] < row) {
                        row = f[0];
                        col = f[1];
                    } else if (f[0] == row && f[1] < col) {
                        row = f[0];
                        col = f[1];
                    }
                }
            }

            if (minIdx == -1) break; // 잡아먹을 수 있는 물고기 없으면 break
            else if (minDis > 0) { // 물고기 먹을 수 있을 때
                int x = fish.get(minIdx)[0];
                int y = fish.get(minIdx)[1];
                area[x][y] = 0; // 먹은 구역 정리
                shark[0] = x; // 상어 위치 정리
                shark[1] = y;

                result += minDis; // 거리 계산
                if (++eatCount == shark[2]) {
                    shark[2]++;
                    eatCount = 0;
                }

                fish.remove(minIdx); // 잡은 물고기 삭제
            }
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        result = 0;
        eatCount = 0;

        fish = new ArrayList<>();
        area = new int[N][N]; // 0: 빈칸, 1~6 칸에 있는 물고기 크기, 9: 아기상어
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                area[i][j] = num;

                if (num == 0) continue;
                if (num == 9) {
                    shark = new int[]{i, j, 2};
                    area[i][j] = 0;
                } else fish.add(new int[]{i, j, num});
            }
        }

        fishOrder();

        System.out.println(result);
        br.close();
    }
}
import java.io.*;
import java.util.*;

public class Solution {
    static int N, result;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dis;

        public Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis < o.dis ? -1 : 1;
        }
    }

    static void bfs(int sum, int x, int y) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(x, y, 0));
        visit[x][y] = true;

        while (!que.isEmpty()) {
            Node temp = que.poll();

            if (temp.x == N - 1 && temp.y == N - 1) {
                result = Math.min(result, temp.dis);
                continue;
            }

            for (int d = 0; d < dx.length; d++) {
                int nextX = temp.x + dx[d];
                int nextY = temp.y + dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;

                if (visit[nextX][nextY]) continue;

                que.offer(new Node(nextX, nextY, temp.dis + map[nextX][nextY]));
                visit[nextX][nextY] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            result = Integer.MAX_VALUE;

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            visit = new boolean[N][N];
            bfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
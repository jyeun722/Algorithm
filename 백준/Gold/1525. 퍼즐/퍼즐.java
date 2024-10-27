import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        String str;
        int x;
        int y;
        int cnt;

        public Node(String str, int x, int y, int cnt) {
            this.str = str;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(String arr, int x, int y) {
        HashMap<String, Boolean> map = new HashMap<>();
        Deque<Node> que = new LinkedList<>();
        que.offer(new Node(arr, x, y, 0));

        while (!que.isEmpty()) {
            Node node = que.poll();
            if (node.str.equals("123456780")) {
                System.out.println(node.cnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nextX = node.x + dx[d];
                int nextY = node.y + dy[d];
                int next = nextX * 3 + nextY;

                if (nextX < 0 || nextX > 2 || nextY < 0 || nextY > 2) continue;

                char changeChar = node.str.charAt(next); // 바꿀 번호
                String tmp = node.str.replace(changeChar, 'a');
                tmp = tmp.replace('0', changeChar);
                tmp = tmp.replace('a', '0');

                if (map.containsKey(tmp)) continue;
                map.put(tmp, true);
                que.offer(new Node(tmp, nextX, nextY, node.cnt + 1));
            }
        }

        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int x = -1, y = 1;
        String arr = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                String s = st.nextToken();
                arr += s;
                if (s.equals("0")) {
                    x = i;
                    y = j;
                }
            }
        }

        bfs(arr, x, y);

        br.close();
    }
}
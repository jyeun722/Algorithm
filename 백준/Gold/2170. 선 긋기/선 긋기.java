import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            else return Integer.compare(this.y, o.y);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 선을 그은 횟수
        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }
        Arrays.sort(nodes);

        int start = nodes[0].x;
        int end = nodes[0].y;
        int distance = end - start;

        for (int i = 1; i < N; i++) {
            Node node = nodes[i];

            // start <= node.x => 정렬이므로
            if (end >= node.y) {
                // 범위 안에 아에 겹치는 경우
                continue;
            } else if (end >= node.x) {
                // 끝만 더 늘어난 경우
                distance += node.y - end;
            } else { // end < node.y
                // 아에 새로운 범위에 시작 ~ 끝 있는 경우
                distance += node.y - node.x;
            }

            // start 갱신 불필요: 어차피 x 기준 오름차순 정렬
            end = node.y;
        }

        System.out.println(distance);
        br.close();
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        long start, end;

        public Node(long start, long end) {
            this.start = start; this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수상 택시를 타려고 하는 사람
        int M = Integer.parseInt(st.nextToken()); // 집의 개수

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long A = Integer.parseInt(st.nextToken()); // 출발지
            long B = Integer.parseInt(st.nextToken()); // 목적지

            if (A <= B) continue; // 정방향은 어차피 가야하므로 pass
            nodes.add(new Node(A, B));
        }
        Collections.sort(nodes);

        /* 출발, 목적
         3 1
         4 2
         12 11
         14 11
         14 13
        */

        long distance = M;
        if (!nodes.isEmpty()) { // 역방향이 있는 경우
            long start = nodes.get(0).start;
            long end = nodes.get(0).end;

            for (Node node : nodes) {
                // 출발 -> 도착하는 거리가 겹치는 경우
                if (node.end <= start) {
                    // max: 아에 겹치는 경우 고려
                    start = Math.max(node.start, start);
                } else {
                    distance += (start - end) * 2; // 왕복이므로 * 2
                    start = node.start;
                    end = node.end;
                }
            }
            distance += (start - end) * 2;
            // 15(M) + 6 + 6 = 15 + 12 = 27
        }

        System.out.println(distance);
        br.close();
    }
}
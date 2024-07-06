import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start; this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.end, o.end);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 사람 수
        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(Math.min(h, o), Math.max(h, o));
        }
        int D = Integer.parseInt(br.readLine()); // 철로의 길이
        Arrays.sort(nodes);

        PriorityQueue<Integer> starts = new PriorityQueue<>();
        int maxCnt = 0;
        for (Node node : nodes) { // 목적지 기준 오름차순 정렬
            starts.offer(node.start);

            // 가능한 거리보다 (끝 점 - 시작 점)이 커버리면 불가능 -> 해당 시작점 빼기
            while (!starts.isEmpty() && D < node.end - starts.peek()) starts.poll();
            maxCnt = Math.max(maxCnt, starts.size());
        }

        System.out.println(maxCnt);
        br.close();
    }
}
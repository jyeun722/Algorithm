import java.util.*;
import java.io.*;

public class Main {

    static class Gwanak implements Comparable<Gwanak> {
        int gj; // 목적지 노드
        double len; // 간선의 가중치
        int number; // 늑대의 이동 상태 (0: 빠른 이동, 1: 느린 이동)

        Gwanak(int j, double len) { // 여우용 생성자
            this.gj = j;
            this.len = len;        
        }

        Gwanak(int j, double len, int number) { // 늑대용 생성자
            this.gj = j;
            this.len = len;
            this.number = number;
        }

        @Override
        public int compareTo(Gwanak other) {
            return Double.compare(this.len, other.len);
        }
    }

    static int N, M;
    static List<Gwanak>[] map;
    static double[] fox;
    static double[][] wolf;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N + 1];
        fox = new double[N + 1];
        wolf = new double[N + 1][2];

        Arrays.fill(fox, Double.MAX_VALUE); // 여우 최단 거리 초기화
        for (int i = 1; i <= N; i++) {
        	Arrays.fill(wolf[i], Double.MAX_VALUE);
            map[i] = new ArrayList<>();
        }
        fox[1] = 0; // 시작 노드 초기화
        wolf[1][0] = 0; // 늑대 시작 노드 초기화(첫 번째 방문)

        int sa, sb;
        double slen;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            sa = Integer.parseInt(st.nextToken());
            sb = Integer.parseInt(st.nextToken());
            slen = Double.parseDouble(st.nextToken());

            map[sa].add(new Gwanak(sb, slen));
            map[sb].add(new Gwanak(sa, slen)); // 양방향 간선 추가
        }

        foxDijkstra();
        wolfDijkstra();

        int answer = 0;
        for (int i = 1; i <= N; i++) {
        	//System.out.println(i+" "+fox[i]+" "+Math.min(wolf[i][0], wolf[i][1]));
            if (fox[i] < Math.min(wolf[i][0], wolf[i][1])) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void foxDijkstra() {
        PriorityQueue<Gwanak> pq = new PriorityQueue<>();
        pq.add(new Gwanak(1, 0));
        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Gwanak current = pq.poll();
            int currentNode = current.gj;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;
            for (Gwanak next : map[currentNode]) {
                if (fox[next.gj] > fox[currentNode] + next.len) {
                    fox[next.gj] = fox[currentNode] + next.len;
                    pq.add(new Gwanak(next.gj, fox[next.gj]));
                }
            }
        }
    }

    static void wolfDijkstra() {
        PriorityQueue<Gwanak> pq = new PriorityQueue<>();
        pq.add(new Gwanak(1, 0, 0)); // 첫 방문(빠른 이동)
        boolean[][] visited = new boolean[N + 1][2]; // 노드 방문 상태

        while (!pq.isEmpty()) {
            Gwanak current = pq.poll();
            int currentNode = current.gj;
            int state = current.number; // 현재 상태 (빠르면 0, 느리면 1)

            if (visited[currentNode][state]) continue;

            visited[currentNode][state] = true;
            for (Gwanak next : map[currentNode]) {
                double nextLen = state == 0 ? next.len / 2 : next.len * 2; // 이동 상태에 따른 길이 조정
                int nextState = 1 - state; // 상태 전환
                if (wolf[next.gj][nextState] > wolf[currentNode][state] + nextLen) {
                    wolf[next.gj][nextState] = wolf[currentNode][state] + nextLen;
                    pq.add(new Gwanak(next.gj, wolf[next.gj][nextState], nextState));
                }
            }
        }
    }

}
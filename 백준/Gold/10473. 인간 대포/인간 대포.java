import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx;
        double time;

        public Node(int idx, double time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.time, o.time);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        double[] curLoc = new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) };
        st = new StringTokenizer(br.readLine());
        double[] desLoc = new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) };

        int n = Integer.parseInt(br.readLine());
        double[][] cannon = new double[n + 2][2];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            cannon[i] = new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) };
        }
        cannon[0] = curLoc;
        cannon[n + 1] = desLoc;

        double[][] arr = new double[n + 2][n + 2];

        for (int j = 1; j < n + 2; j++) {
            double d = Math.sqrt(Math.pow((cannon[0][0] - cannon[j][0]), 2) + Math.pow((cannon[0][1] - cannon[j][1]), 2));
            arr[0][j] = d * 0.2;
            arr[j][0] = d * 0.2;
        }

        for (int i = 1; i < n + 1; i++) {
            for(int j = i + 1; j < n + 2; j++) {
                double d = Math.sqrt(Math.pow((cannon[i][0] - cannon[j][0]), 2) + Math.pow((cannon[i][1] - cannon[j][1]), 2));
                double min = Math.min(d * 0.2, Math.abs(d - 50) * 0.2 + 2);
                arr[i][j] = min;
                arr[j][i] = min;
            }
        }

        double[] minEdge = new double[n + 2];
        Arrays.fill(minEdge, Double.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        minEdge[0] = 0;
        pq.offer(new Node(0, minEdge[0]));

        while (!pq.isEmpty()) {
            Node temp = pq.poll();

            for (int j = 0; j < n + 2; j++) {
                if (arr[temp.idx][j] != 0 && minEdge[j] > temp.time + arr[temp.idx][j]) {
                    minEdge[j] = temp.time + arr[temp.idx][j];
                    pq.offer(new Node(j, minEdge[j]));
                }
            }
        }

        System.out.println(minEdge[n + 1]);
        br.close();
    }
}
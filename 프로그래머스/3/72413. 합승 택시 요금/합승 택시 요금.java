import java.util.*;
import java.io.*;
class Solution {
    static class Node implements Comparable<Node> {
        int end,w;
        public Node(int end, int w){
            this.end = end;
            this.w = w;
        }
        @Override
        public int compareTo(Node o){
            return this.w-o.w;
        }
    }
    
    static boolean[] v;
    static int[] dist;
    static int[][] cost;
    static List<Node>[] list;
    
    static final int INF = 100_000_000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        list = new List[n+1];
        cost = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] far: fares){
            list[far[0]].add(new Node(far[1], far[2]));
            list[far[1]].add(new Node(far[0], far[2]));
        }
  
        for(int i=1; i<=n; i++){
            dijkstra(i,n);
            for(int j=1; j<=n; j++){
                cost[i][j]=dist[j];
            }
        }
        
        for(int i=1; i<=n; i++){
            answer = Math.min(cost[s][i]+cost[i][a]+cost[i][b], answer);
        }

        return answer;
    }
    
    static void dijkstra(int start, int n){
        v = new boolean[n+1];
        dist = new int[n+1];
        for(int i=1; i<=n; i++){
            dist[i]=INF;
        }
        
        int cnt=0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start]=0;
        pq.offer(new Node(start,0));
        
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            int end=cur.end;
            
            if(v[end]) continue;
            if(++cnt>n) break;
            v[end]=true;
            
            for(Node node:list[end]){
                if( dist[node.end] > dist[end] + node.w){
                    dist[node.end] = dist[end] + node.w;
                    pq.offer(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}
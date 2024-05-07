import java.util.*;
import java.io.*;




public class Main {

	static class Node implements Comparable<Node>{
		int pos;
		int weight;
		
		Node(int next, int weight){
			this.pos=next;
			this.weight=weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
		
		
	}
	
	static int N,E;
	static List<Node>[] list;
	static boolean[] v;
	static int[] answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        v=new boolean[N+1];
        answer=new int[N+1];
        
        list=new List[N+1];
        for(int i=1;i<=N;i++) {
        	list[i]=new ArrayList<>();
        }
        
        int w,fir,sec;
        for(int i=0;i<E;i++) {
        	st=new StringTokenizer(br.readLine());
        	fir=Integer.parseInt(st.nextToken());
        	sec=Integer.parseInt(st.nextToken());
        	w=Integer.parseInt(st.nextToken());
        	
        	list[fir].add(new Node(sec,w));
        	list[sec].add(new Node(fir,w));
        }
    	st=new StringTokenizer(br.readLine());
    	int v1=Integer.parseInt(st.nextToken());
    	int v2=Integer.parseInt(st.nextToken());
        
        
        int ans1=0;
        ans1+=dijkstra(1, v1);
        ans1+=dijkstra(v1, v2);
        ans1+=dijkstra(v2, N);
        
        
        int ans2=0;
        ans2+=dijkstra(1, v2);
        ans2+=dijkstra(v2, v1);
        ans2+=dijkstra(v1, N);
        
        
        int goal = (ans1 >= 200000000 && ans2 >= 200000000) ? -1 : Math.min(ans1, ans2);
        System.out.println(goal);
    }
    
    static int dijkstra(int start, int end) {
    	Arrays.fill(v, false);
    	Arrays.fill(answer, 200000000);
    	PriorityQueue<Node> pq=new PriorityQueue<>();
    	answer[start] = 0;
    	pq.add(new Node(start,0));
        Node node;
        while(!pq.isEmpty()) {
        	node=pq.poll();
        	
        	if(v[node.pos]) continue;
        	v[node.pos]=true;
        	if(v[end])break;
        	
        	for(Node next:list[node.pos]) {
        		if(!v[next.pos]&&answer[next.pos]>answer[node.pos]+next.weight) {
        			//System.out.println("갱신");
        			answer[next.pos]=answer[node.pos]+next.weight;
        			pq.add(new Node(next.pos,answer[next.pos]));
        		}
        	}
        }

        return answer[end];
    }
}
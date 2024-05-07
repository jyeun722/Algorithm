import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	static int[] home;
	static int[][] bearShop;
	static int[] goal;
	static int n;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=0;
        int M=0;
        int T=Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++) {
        	 n=Integer.parseInt(br.readLine());
             StringTokenizer st; 
             bearShop=new int[n][2];
             home=new int[2];
             goal=new int[2];
             st = new StringTokenizer(br.readLine());
             home[0]=Integer.parseInt(st.nextToken());
        	 home[1]=Integer.parseInt(st.nextToken());
             
             for(int i=0;i<n;i++) {
            	 st = new StringTokenizer(br.readLine());
            	 bearShop[i][0]=Integer.parseInt(st.nextToken());
            	 bearShop[i][1]=Integer.parseInt(st.nextToken());
             }
             
             st = new StringTokenizer(br.readLine());
             goal[0]=Integer.parseInt(st.nextToken());
        	 goal[1]=Integer.parseInt(st.nextToken());
        	 
        	 N=goal[0];
        	 M=goal[1];
        	 
        	 if(helper()) {
        		 System.out.println("happy");
        	 }else {
        		 System.out.println("sad");
        	 }
        	 
             
        }

    }
	static boolean helper() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		q.add(new int[] {home[0],home[1]});
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0], py = pos[1];
			if(Math.abs(px-goal[0]) + Math.abs(py-goal[1]) <= 1000) {// 지금 위치에서 목적지까지 맥주 20병으로 갈 수 있는가.
				return true;
			}
			for(int i=0; i<n; i++) {//갈 수 없다면
				if(!visited[i]) {// 방문하지 않은 편의점 중 갈 수 있는 편의점이 있는가
					int nx = bearShop[i][0], ny = bearShop[i][1];
					int dis = Math.abs(px - nx) + Math.abs(py - ny);
					if(dis <= 1000) {//20병으로 그곳을 갈 수 있는가.
						visited[i] = true;
						q.add(new int[]{nx,ny});
					}
				}
			}
		}
		return false;
	}
}
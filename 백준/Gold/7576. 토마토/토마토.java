import java.util.*;
import java.io.*;

public class Main {

	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        int M,N;
        int max=0;
        int count=0;
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        
        int[] di=new int[] {1,0,-1,0};
        int[] dj=new int[] {0,1,0,-1};
        Queue<Integer> q=new ArrayDeque<Integer>();
        int[][] map=new int[N][M];
        for(int i=0;i<N;i++) {
        	st=new StringTokenizer(br.readLine());
        	
        	for(int j=0;j<M;j++) {
        		map[i][j]=Integer.parseInt(st.nextToken());
        		if(map[i][j]==1) {
        			q.add(i*M+j);
        		}
        		if(map[i][j]==0) {
        			 max++;//다 익어야 하는 수
        		}
        		
        	}
        }
        
        if(count==max) {
        	System.out.println(0);
        	return;
        }
        
        int sub,check,ni,nj;
        int answer=0;
        
        while(!q.isEmpty()) {
        	//System.out.println(answer);
        	check=q.size();
        	answer++;
        	for(int i=0;i<check;i++) {
        		sub=q.poll();
        		for(int a=0;a<4;a++){
        			ni=sub/M+di[a];
        			nj=sub%M+dj[a];    
        			if(ni>=0&&ni<N&&nj>=0&&nj<M&&map[ni][nj]==0) {//맵 안쪽이고 토마토가 있으면
        				map[ni][nj]=1;
        				q.add(ni*M+nj);
        				count++;
        			}
        		
        		}
    			if(count==max) {
    				q.clear();
    				break;
    			}
        	}
        	
//            for(int i=0;i<N;i++) {
//            	for(int j=0;j<M;j++) {
//            		System.out.print(map[i][j]);
//            	}
//            	System.out.println();
//            }
        }

        if(count!=max) {
        	//System.out.println("익은 개수: "+count);
        	//System.out.println("턴 수: "+answer);
        	System.out.println(-1);
        	return;
        }
        
//    	System.out.println("익은 개수: "+count);
//    	System.out.println("턴 수: "+answer);
        System.out.println(answer);
        
        
        
    }
}
import java.util.*;
import java.io.*;

public class Main {
    static long[] arr = new long[101]; // 파도반 수열 값을 저장할 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
        int N,M;
        boolean[] v=new boolean[101];
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++) {
        	st=new StringTokenizer(br.readLine());
        	map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        for(int i=0;i<M;i++) {
       	 	st=new StringTokenizer(br.readLine());
        	map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
       	
       }
       Queue<Integer> q=new ArrayDeque<Integer>();
       q.add(1);
       int check,pos,sub;
       int answer=0;
       while(!q.isEmpty()) {
    	   answer++;
    	   check=q.size();
    	   for(int i=0;i<check;i++) {
    		   pos=q.poll();
    		   for(int j=1;j<=6;j++) {
    			   sub=pos+j;
    			 if(sub<=100) {
      			   if(v[sub]) continue;
      			   v[sub]=true;
      			   if(map.containsKey(sub)) {
      				   v[map.get(sub)]=true;
      				   q.add(map.get(sub));	
      			   }else {
      				   q.add(sub);
      			   }
    			 }
    		   }
    	   }
           if(v[100])break;
       } 
       System.out.println(answer);
        
    }
}
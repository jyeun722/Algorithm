import java.util.*;
import java.io.*;

public class Main {

	static PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Collections.reverseOrder());
	static PriorityQueue<Integer> rpq;
	static int answer=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
        	pq.add(Integer.parseInt(br.readLine()));
        }

        int fir;
        int sec;
        while(!pq.isEmpty()) {
        	if(pq.size()>=2) {
        		fir=pq.poll();
        		sec=pq.poll();
//        		System.out.println("fir: "+fir);
//        		System.out.println("sec: "+sec);
        		if((fir==1||sec==1)&&sec>0) {
        			answer+=fir;
        			answer+=sec;
        			continue;
        		}
        		if(fir>0&&sec>0) {
        			answer+=fir*sec;
        		}else if(fir>0&&sec==0) {
        			answer+=fir;
        			if(pq.size()%2==0) {
        				answer+=sec;
        			}else{
        				pq.add(sec);
        			}
        		}
        		else if(fir>0&&sec<0) {
        			if(pq.size()%2==0) {
        				answer+=fir;
        				answer+=sec;
        			}else{
        				answer+=fir;
        				pq.add(sec);
        			}
        			helper();
        		}
        		else if(fir==0) {
        			if(pq.size()%2==0) {
        				
        			}else{
        				pq.add(sec);
        			}
        			helper();
        		}
        		else if(fir<0) {
        			pq.add(fir);
        			pq.add(sec);
        			helper();
        		}
        	}else {
        		answer+=pq.poll();
        	}
        }
        
        System.out.println(answer);
    }
    static void helper() {
    	rpq=new PriorityQueue<Integer>();
    	// 들어왔으면 음수만 남음
    	while(!pq.isEmpty()) {
			rpq.add(pq.poll());
		}
    	int fir;
    	int sec;
        while(!rpq.isEmpty()) {
        	if(rpq.size()>=2) {
        		fir=rpq.poll();
        		sec=rpq.poll();
//        		System.out.println("fir: "+fir);
//        		System.out.println("sec: "+sec);
        		answer+=fir*sec;
        	}else {//1개 남음
        		answer+=rpq.poll();
        		break;
        	}
        }
    }
}
import java.util.*;
import java.io.*;

public class Main {
	static int[] popul;
	
	static int N;
	static int min=Integer.MAX_VALUE;
	static List<Integer>[] city;
	static List<Integer>[] groups=new List[2];
	
	static boolean v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        popul=new int[N+1];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
        	popul[i]=Integer.parseInt(st.nextToken());
        }
        groups[0]=new ArrayList<>();
        groups[1]=new ArrayList<>();
        city=new List[N+1];
        int size;
        for(int i=1;i<=N;i++) {
        	st=new StringTokenizer(br.readLine());
        	city[i]=new ArrayList<>();
        	size=Integer.parseInt(st.nextToken());
        	for(int j=0;j<size;j++) {
        		city[i].add(Integer.parseInt(st.nextToken()));
        	}

        }
        comb(1);
        if(min==Integer.MAX_VALUE) {
        	System.out.println(-1);
        	return;
        }
        System.out.println(min);
        
        
    }
    
    static void comb(int pos) {
    	
    	if(pos==N+1) {
    		if(groups[0].size()==0||groups[1].size()==0) {
    			return;
    		}
    		if(check()) {
    			//System.out.println("연산");
    			min=Math.min(min,cal());
    		}
    		return;
    	}
    	
    	
    	groups[0].add(pos);
    	comb(pos+1);
    	groups[0].remove(groups[0].size()-1);
    	
    	groups[1].add(pos);
    	comb(pos+1);
    	groups[1].remove(groups[1].size()-1);
    	
    }
    
    static boolean check() {
    	//System.out.println("check 실행");
        if (!isConnected(groups[0])) {
            return false;
        }

        return isConnected(groups[1]);
    }

    static boolean isConnected(List<Integer> group) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : city[node]) {
                if (group.contains(neighbor) && !visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    count++;
                }
            }
        }
        return count == group.size();
    }
    
    static int cal() {
    	int a=0;
    	for(int sub:groups[0]) {
    		a+=popul[sub];
    	}
    	
    	int b=0;
    	for(int sub:groups[1]) {
    		b+=popul[sub];
    	}
    	
    	return Math.abs(a-b);
    }
}
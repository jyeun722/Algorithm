import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> que = new PriorityQueue<>(
        		(i1, i2) -> Math.abs(i1) == Math.abs(i2) 
        		? Integer.compare(i1, i2) : Integer.compare(Math.abs(i1), Math.abs(i2)));
        
        for (int i = 0; i < N; i++) {
        	int x = Integer.parseInt(br.readLine());
        	
        	if (x == 0) {
        		if (que.isEmpty()) sb.append(0).append("\n");
        		else sb.append(que.poll()).append("\n");
        	} else {
        		que.offer(x);
        	}
        }
        
        System.out.println(sb);
        br.close();
	}
}

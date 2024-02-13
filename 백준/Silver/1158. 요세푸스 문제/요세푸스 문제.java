import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        sb.append("<");
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) que.offer(i);
        
        while (!que.isEmpty()) {
        	for (int i = 0; i < K - 1; i++) {
        		que.offer(que.poll());
        	}
        	sb.append(que.poll()).append(", ");
        }
         
        String str = sb.toString();
        str = str.substring(0, str.length() - 2);
        
        System.out.println(str + ">");
        br.close();
	}
}

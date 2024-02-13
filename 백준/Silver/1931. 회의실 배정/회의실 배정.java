import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 회의실 수
        
        PriorityQueue<int[]> meeting = new PriorityQueue<>(
        		(i1, i2) -> i1[1] != i2[1] ? Integer.compare(i1[1], i2[1]) : Integer.compare(i1[0], i2[0]));
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	meeting.offer(new int[] {start, end});
        }
        
        int result = 1;
        int start = meeting.poll()[1];
        while (!meeting.isEmpty()) {
        	int[] temp = meeting.poll();
        	
        	if (temp[0] >= start) {
        		start = temp[1];
        		result++;
        	}
        }
        
        System.out.println(result);
        br.close();
	}
}
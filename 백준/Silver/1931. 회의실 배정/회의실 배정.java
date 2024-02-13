import java.io.*;
import java.util.*;

public class Main {
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		
		 Meeting(int start, int end) {
			 this.start = start;
			 this.end = end;
		 }
		 
		 @Override
		 public int compareTo(Meeting o) {
			 return this.end != o.end ? this.end - o.end : this.start - o.start;
		 }
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 회의실 수
        Meeting[] meetings = new Meeting[N];
        
        for (int i = 0; i < N; i++) {
         	st = new StringTokenizer(br.readLine());
         	int start = Integer.parseInt(st.nextToken());
         	int end = Integer.parseInt(st.nextToken());
         	meetings[i] = new Meeting(start, end);
         }
         
        Arrays.sort(meetings);
         
        int result = 1;
        int start = meetings[0].end;
        for (int i = 1; i < N; i++) {
        	if (meetings[i].start >= start) {
        		start = meetings[i].end;
        		result++;
        	}
        }
        
        System.out.println(result);
        br.close();
	}
}
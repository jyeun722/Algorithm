import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) que.add(i);
		
		boolean throwAway = true;
		while (que.size() != 1) {
			if (throwAway) {
				que.poll();
				throwAway = false;
				continue;
			} else {
				que.add(que.poll());
				throwAway = true;
			}
		}
		
		System.out.println(que.poll());
		
		br.close();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int result;
	
	static int spread(int number, int ele, String[] button, int M) {
		int cnt = 0;
		while (true) {
			if (number < 0 || cnt >= result) return -1;
			String str = Integer.toString(number);
			
			boolean flag = false;
			for (int i = 0; i < M; i++) {
				if (str.contains(button[i])) {
					number += ele;
					cnt++;
					flag = true;
                    break;
				}
			} 
			
			if (!flag) return cnt + str.length();
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 이동하려는 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼의 개수
        
        String[] button = new String[M];
        if (M > 0) {
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		button[i] = st.nextToken();
        	}
        }
        
        result = Math.abs(N - 100);
        
        int[] elements = {-1, 1};
        for (int i = 0; i < elements.length; i++) {
        	int n = spread(N, elements[i], button, M);
        	if (n != -1) result = Math.min(result, n);
        }
         
        System.out.println(result);
        br.close();
	}
}

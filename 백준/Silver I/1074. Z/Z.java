import java.io.*;
import java.util.*;

public class Main {
	static int size, r, c, order;
	
	static void divide(int N, int x, int y) {
		if (N == 1) {
			return;
		} else {
			if (r < x + N / 2 && c < y + N / 2) {
				divide(N / 2, x, y);
				return;
			} else if (r < x + N / 2 && c >= y + N / 2) {
				order += (N / 2) * (N / 2);
				divide(N / 2, x, y + N / 2);
				return;
			} else if (r >= x + N / 2 && c < y + N / 2) {
				order += (N / 2) * N;
				divide(N / 2, x + N / 2, y);
				return;
			} else {
				order += N * N - (N / 2) * (N / 2);
				divide(N / 2, x + N / 2, y + N / 2);
				return;
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        size = (int) Math.pow(2, N);
        
        order = 0;
        divide(size, 0, 0);
        
        System.out.println(order);
        
        br.close();
	}
}

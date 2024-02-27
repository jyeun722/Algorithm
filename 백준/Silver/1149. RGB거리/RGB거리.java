import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[][] home = new int[N][3];
        
        st = new StringTokenizer(br.readLine());
    	for (int j = 0; j < 3; j++) {
    		home[0][j] = Integer.parseInt(st.nextToken());
    	}
        
        for (int i = 1; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 3; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		
        		int idx = (j + 1) % 3;
        		int idx2 = (j + 2) % 3;
        		int min = Math.min(home[i - 1][idx], home[i - 1][idx2]);
        		
        		home[i][j] = num + min;
        	}
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
        	min = Math.min(min, home[N - 1][i]);
        }
         
        System.out.println(min);
        br.close();
	}
}

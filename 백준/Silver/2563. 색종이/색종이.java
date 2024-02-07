import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int result = 0;
        
        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[100][100];
        
        for (int n = 0; n < N; n++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	for (int i = x; i < x + 10; i++) {
        		for (int j = y; j < y + 10; j++) {
        			paper[i][j] = 1;
        		}
        	}
        }
        
        for (int i = 0; i < 100; i++) {
        	for (int j = 0; j <100; j++) {
        		if (paper[i][j] == 1) result++;
        	}
        }
        
        System.out.println(result);
        br.close();
	}
}

import java.util.*;
import java.io.*;

public class Main {
	static int N, result = Integer.MAX_VALUE;
	static int[] S, B;
	static boolean[] ingredients;
	static void subs(int cnt, int sTaste, int bTaste) {
		if (cnt == N) {
			if (sTaste == 1 && bTaste == 0) return;
			int num = Math.abs(sTaste - bTaste);
			if (num < result) result = num;
			return;
		}
		ingredients[cnt] = true;
		subs(cnt + 1, sTaste * S[cnt], bTaste + B[cnt]);
		ingredients[cnt] = false;
		subs(cnt + 1, sTaste, bTaste);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        S = new int[N];
		B = new int[N];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	S[i] = Integer.parseInt(st.nextToken());
        	B[i] = Integer.parseInt(st.nextToken());
        }
        
        ingredients = new boolean[N];
        subs(0, 1, 0);
        
        System.out.println(result);
        br.close();
	}
}

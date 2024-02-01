import java.io.*;
import java.util.*;

public class Main {
	static int S, P;
	static int[] limitCnts, cnts;
	static char[] alpha = {'A', 'C', 'G', 'T'};
	
	static boolean checkInit(String str, int start, int end) {
		for (int i = start; i < end; i++) {
			int idx = Arrays.binarySearch(alpha, str.charAt(i));
			if (idx != -1) cnts[idx]++;
		}
		for (int i = 0; i < 4; i++) {
			if (limitCnts[i] > cnts[i]) return false;
		}
		
		return true;
	}
	
	static boolean check(char start, char end) {
		int idx = Arrays.binarySearch(alpha, start);
		if (idx != -1) cnts[idx]--;
		idx = Arrays.binarySearch(alpha, end);
		if (idx != -1) cnts[idx]++;
		
		for (int i = 0; i < 4; i++) {
			if (limitCnts[i] > cnts[i]) return false;
		}
		
		return true;
	}
	
	static boolean check2(String str) {
		for (int i = 0; i < 4; i++) {
			int size = str.length() - str.replaceAll(alpha[i] + "", "").length();
			if (size < limitCnts[i]) return false;
		}
		return true;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        String str = br.readLine();
        cnts = new int[4];
        
        limitCnts = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) limitCnts[i] = Integer.parseInt(st.nextToken());
        
        int result = 0;
        
        if (checkInit(str, 0, P)) result++;
        for (int i = 0; i < S - P; i++) { 
        	if (check(str.charAt(i), str.charAt(i + P))) result++;
        }
        
        System.out.println(result);
        br.close();
	}
}

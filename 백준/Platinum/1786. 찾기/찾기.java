import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        char[] txt = br.readLine().toCharArray();
        char[] ptn = br.readLine().toCharArray();
        
        int size = ptn.length;
        int[] pi = new int[size];
        for (int i = 1, j = 0; i < size; i++) {
        	while(j > 0 && ptn[i] != ptn[j]) j = pi[j - 1];
        	if (ptn[i] == ptn[j]) pi[i] = ++j;
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0, j = 0; i < txt.length; i++) {
        	while(j > 0 && txt[i] != ptn[j]) j = pi[j - 1];
        	if (txt[i] == ptn[j]) {
        		if (++j == size) {
        			answer.add(i - j + 2);
        			j = pi[j - 1];
        		}
        	}
        }
        
        sb.append(answer.size()).append("\n");
        for (int n : answer) sb.append(n).append(" ");
        
        System.out.println(sb);
        
        br.close();
	}
}

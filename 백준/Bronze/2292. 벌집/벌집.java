import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int layer = 1;
        
        while (N > 1) {
        	N -= (6 * layer);
        	layer++;
        }
    	System.out.println(layer);
        
        br.close();
	}
}

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int layer = 1, six = 0;
        
        if (N != 1) layer++;
    	for (int i = 2; i < N + 1; i++) {
    		if (++six == 6 * (layer - 1) + 1) {
    			layer++;
    			six = 1;
    		}
    	}
    	System.out.println(layer);
        
        br.close();
	}
}

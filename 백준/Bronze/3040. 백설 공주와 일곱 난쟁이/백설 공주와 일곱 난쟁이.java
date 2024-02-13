import java.io.*;

public class Main {
	static int[] seven, nine;
	static StringBuilder sb;
	
	public static void comb(int cnt, int start, int sum) {
		if (cnt == 7) {
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					sb.append(seven[i]).append("\n");
				}
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			seven[cnt] = nine[i];
			comb(cnt + 1, i + 1, sum + nine[i]);
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        nine = new int[9];
        seven = new int[7];
        
        for (int i = 0; i < 9; i++) {
        	nine[i] = Integer.parseInt(br.readLine());
        }

        comb(0, 0, 0);
        System.out.println(sb);
        
        br.close();
	}
}
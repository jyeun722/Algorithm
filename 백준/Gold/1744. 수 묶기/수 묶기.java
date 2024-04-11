import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int zero = 0, one = 0;
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	int num = Integer.parseInt(br.readLine());
        	if (num == 0) zero++;
        	else if (num == 1) one++;
        	else if (num < 0) minus.add(num);
        	else plus.add(num);
        }
        Collections.sort(plus);
        Collections.sort(minus, Collections.reverseOrder());
        int sum = 0;
        
        for (int i = minus.size() - 1; i > 0; i -= 2) {
        	sum += minus.get(i) * minus.get(i - 1);
        }
        if (minus.size() % 2 != 0 && zero < 1) sum += minus.get(0);
        
        for (int i = plus.size() - 1; i > 0; i -= 2) {
        	sum += plus.get(i) * plus.get(i - 1);
        }
        if (plus.size() % 2 != 0) sum += plus.get(0);
        
        System.out.println(sum + one);
        br.close();
	}
}

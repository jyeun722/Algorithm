import java.io.*;
import java.util.*;

public class Main {
	static class Jewel implements Comparable<Jewel> {
		int wei, value;
		
		public Jewel(int wei, int value) {
			this.wei = wei; this.value = value;
		}
		
		@Override
		public int compareTo(Jewel o) {
			if (this.wei == o.wei) return o.value - this.value;
			else return this.wei - o.wei;
		}
	}
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int m = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	jewels.add(new Jewel(m, v));
        }
        Collections.sort(jewels);
        
        ArrayList<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
        	bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);
        
        long sum = 0;
        PriorityQueue<Integer> jBags = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
        	while (j < N && jewels.get(j).wei <= bags.get(i)) {
        		jBags.add(jewels.get(j++).value);
        	}
        	
        	if (!jBags.isEmpty()) {
        		sum += jBags.poll();
        	}
        }
        
        System.out.println(sum);
        br.close();
	}
}

import java.io.*;
import java.util.*;
public class Main {
	static int H,W;
	static int[] blocks;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		blocks = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			blocks[i]=Integer.parseInt(st.nextToken());
		}
		int total = 0;
		for(int i=1; i<W-1; i++) {
			int maxLeft = 0; // maxLeft 구하기
			for(int j=0; j<i; j++) {
				if( maxLeft < blocks[j]) {
					maxLeft = blocks[j];
				}
			}
			int maxRight = 0; // maxRight 구하기
			for(int j=i; j<W; j++) {
				if( maxRight < blocks[j]) {
					maxRight = blocks[j];
				}
			}
			int minH = Math.min(maxLeft, maxRight); // 둘중 작은거 기준
			if(minH > blocks[i]) {
				total += minH-blocks[i];
			}
		}
		System.out.println(total);
	}
}
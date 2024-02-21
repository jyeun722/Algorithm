import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] alphabet;
	static char[] cipher;
	static StringBuilder sb;
	// 최소 한 개의 모음(a, e, i, o, u), 최소 두 개의 자음
	// 알파벳 증가하는 순서로 배열, C개로 L길이의 암호 구성
	
	static boolean check(String str) {
		int cnt = 0;
		if (str.contains("a")) cnt++;
		if (str.contains("e")) cnt++;
		if (str.contains("i")) cnt++;
		if (str.contains("o")) cnt++;
		if (str.contains("u")) cnt++;
		
		return str.length() - cnt > 1 && cnt > 0;
	}

	static void comb(int cnt, int start) {
		if (cnt == L) {
			String c = Arrays.toString(cipher).replaceAll("[^a-z]", "");
			if (check(c)) sb.append(c).append("\n");
			
			return;
		}
		for (int i = start; i < C; i++) {
			cipher[cnt] = alphabet[i];
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // L개의 알파벳으로 구성
		C = Integer.parseInt(st.nextToken()); // 알파벳 C종류

		alphabet = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphabet);

		cipher = new char[L];
		
		comb(0, 0);

		System.out.println(sb);
		br.close();
	}
}

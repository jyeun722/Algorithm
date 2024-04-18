import java.io.*;
import java.util.*;

public class Main {
	static boolean count(char[][] map) {
		int len = 3, o = 0, x = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (map[i][j] == 'O') o++;
				else if (map[i][j] == 'X') x++;
			}
		}
		
		HashSet<Character> ws;
		if (x - o == 0) {
			// o가 이기는 경우 -> x가 있으면 안됨
			int check = 0; // 기본
			
			ws = win(map);
			for (char c : ws) {
				if (c == 'O') check = 1;
				else { // X
					check = 2;
					break;
				}
			}
			
			if (check == 2) return false;
			else if (check == 1) return true;
			
			// 승부중인 경우
			if (!full(map)) return false;
			else return true;
		} else if (x - o == 1) {
			// x가 이기는 경우
			int check = 0;
			ws = win(map);
			for (char c : ws) {
				if (c == 'X') check = 1;
				else { // O
					check = 2;
					break;
				}
			}
			if (check == 2) return false;
			else if (check == 1) return true;
			
			// 승부중인 경우
			if (!full(map)) return false;
			else return true;
		}
		
		return false; // 갯수 안맞는 경우
	}
	
	static HashSet<Character> win(char[][] map) {
		HashSet<Character> ws = new HashSet<>();
		
		int len = 3;
		for (int i = 0; i < len; i++) {
			char first = map[i][0];
			if (first == '.') continue;
			
			boolean check = true;
			for (int j = 1; j < len; j++) {
				if (map[i][j] != first) {
					check = false;
					break;
				}
			}
			if (check) ws.add(first);
		}
		for (int j = 0; j < len; j++) {
			char first = map[0][j];
			if (first == '.') continue;
			
			boolean check = true;
			for (int i = 1; i < len; i++) {
				if (map[i][j] != first) {
					check = false;
					break;
				}
			}
			if (check) ws.add(first);
		}
		
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) ws.add(map[1][1]);
		if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) ws.add(map[1][1]);
		
		return ws;
	}
	
	static boolean full(char[][] map) {
		int len = 3;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (map[i][j] == '.') return false;
			}
		}
		return true;
	}
	
	static boolean all(char[][] map) {
		if (!count(map)) return false;
		return true;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = 3;
        char[][] map = new char[len][len];
        String end = br.readLine();
        while (!end.equals("end")) {
        	for (int i = 0; i < len; i++) {
        		for (int j = 0; j < len; j++) {
        			map[i][j] = end.charAt(i * len + j);
        		}
        	}
        	boolean result = all(map);
        	if (result) sb.append("valid\n");
        	else sb.append("invalid\n");
        	end = br.readLine();
        }
        
        System.out.println(sb);
        br.close();
	}
}

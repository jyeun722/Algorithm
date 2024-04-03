import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc < 11; tc++) {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
             
            int check = 1;
             
            ArrayDeque<Character> stack = new ArrayDeque<>();
             
            for (int i = 0; i < N; i++) {
                char c = str.charAt(i);
                if (c == ')' || c == ']' || c == '}' || c == '>') { // close면
                    char before = stack.pollLast();
                    if (c == ')' && before != '(') {
                        check = 0;
                        break;
                    } else if (c == ']' && before != '[') {
                        check = 0;
                        break;
                    } else if (c == '}' && before != '{') {
                        check = 0;
                        break;
                    } else if (c == '>' && before != '<') {
                        check = 0;
                        break;
                    }
                } else {
                    stack.addLast(c);
                }
            }
             
            sb.append("#").append(tc).append(" ").append(check).append("\n");
        }
        System.out.println(sb);
    }
}
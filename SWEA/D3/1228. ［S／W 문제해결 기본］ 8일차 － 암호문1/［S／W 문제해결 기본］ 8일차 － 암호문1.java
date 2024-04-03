import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc < 11; tc++) {
            int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
             
            List<Integer> cipher = new ArrayList<>();
            st = new StringTokenizer(br.readLine()); // 원본 암호문
            for (int i = 0; i < N; i++) {
                cipher.add(Integer.parseInt(st.nextToken()));
            }
             
            int M = Integer.parseInt(br.readLine()); // 명령어의 갯수
             
            st = new StringTokenizer(br.readLine()); // 명령어
            for (int i = 0; i < M; i++) {
                char order = st.nextToken().charAt(0);
                int loc = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                 
                for (int j = 0; j < cnt; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    cipher.add(loc++, num);
                }
            }
             
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) sb.append(cipher.get(i)).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
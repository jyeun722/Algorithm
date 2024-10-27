import java.io.*;
import java.util.*;

public class Main {
    static int move(PriorityQueue<Integer> que, int M) {
        int answer = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            answer += Math.abs(que.peek()) * 2;
            for (int i = 0; i < M && i < size; i++) {
                que.poll();
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> plusArr = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusArr = new PriorityQueue<>();
        int maxNum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) plusArr.offer(num);
            else minusArr.offer(num);

            maxNum = Math.max(maxNum, Math.abs(num));
        }

        int answer = 0;
        answer += move(plusArr, M);
        answer += move(minusArr, M);
        answer -= maxNum;

        System.out.println(answer);

        br.close();
    }
}
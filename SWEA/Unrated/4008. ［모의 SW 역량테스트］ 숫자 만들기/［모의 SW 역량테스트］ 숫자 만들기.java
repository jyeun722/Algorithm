import java.util.*;
import java.io.*;

public class Solution {
    static int N, min, max;
    static int[] nums; // N

    // 0: '+', 1: '-', 2: '*', 3: '/'
    public static void repeat(int[] operCnts, int sum, int cnt) {
        if (cnt == N) { // cnt는 숫자 idx이므로 N으로 들어왔을 때 계산
            if (sum < min) min = sum;
            if (sum > max) max = sum;
            return;
        }
        if (operCnts[0] > 0) { // +
            operCnts[0]--; // 사용한 연산자 줄이고, sum 계산하고 넘겨줌
            repeat(operCnts, sum + nums[cnt], cnt + 1);
            operCnts[0]++; // 다시 추가 시켜서 반복 실행
        }
        if (operCnts[1] > 0) { // -
            operCnts[1]--;
            repeat(operCnts, sum - nums[cnt], cnt + 1);
            operCnts[1]++;
        }
        if (operCnts[2] > 0) { // *
            operCnts[2]--;
            repeat(operCnts, sum * nums[cnt], cnt + 1);
            operCnts[2]++;
        }
        if (operCnts[3] > 0) { // /
            operCnts[3]--;
            repeat(operCnts, sum / nums[cnt], cnt + 1);
            operCnts[3]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            min = Integer.MAX_VALUE; // 최소값을 최댓값으로 세팅
            max = Integer.MIN_VALUE; // 최댓값을 최소값으로 세팅

            N = Integer.parseInt(br.readLine());

            nums = new int[N];
            int[] operCnts = new int[4];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) { // 연산자 숫자들 저장
                operCnts[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) { // 숫자들 순서대로 저장
                nums[i] = Integer.parseInt(st.nextToken());
            }

            repeat(operCnts, nums[0], 1);

            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }
        System.out.println(sb);
    }
}
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // helper 함수로부터 결과를 받아서 answer에 저장하고 출력
        long answer = helper(A, B, C);
        System.out.println(answer);
    }

    static long helper(int a, int b, int c) {
        // 거듭제곱의 베이스 케이스 처리
        if (b == 0) return 1;  // 모든 수의 0제곱은 1
        if (b == 1) return a % c;  // a^1 % c는 a % c

        // 분할 정복으로 문제 해결
        long half = helper(a, b / 2, c);
        long halfPow = (half * half) % c;
        if (b % 2 == 0) return halfPow;  // b가 짝수면 그대로 반환
        else return (halfPow * a) % c;  // b가 홀수면 한 번 더 a를 곱해준다
    }
}
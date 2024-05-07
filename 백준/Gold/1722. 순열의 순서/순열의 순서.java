import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long[] fac = new long[21];
    static int[] goal;
    static boolean[] v;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int oper = Integer.parseInt(st.nextToken());

        fac[0] = 1;
        for (int i = 1; i <= 20; i++) {
            fac[i] = fac[i - 1] * i;
        }
        //System.out.println(Arrays.toString(fac));
        
        if (oper == 1) {
        	
            long goal2;
            goal2 = Long.parseLong(st.nextToken());
            oper1(goal2);
        } else {
            goal = new int[N];
            for (int i = 0; i < N; i++) {
                goal[i] = Integer.parseInt(st.nextToken());
            }
            oper2();
        }
    }

    static void oper1(long goal) {
        int[] answer = new int[N];
        v = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (v[j]) continue;
                if (fac[N - i - 1] < goal) {
                    goal -= fac[N - i - 1];
                } else {
                    answer[i] = j;
                    v[j] = true;
                    break;
                }
            }
        }
        for(int i=0; i<N; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }

    
    static void oper2() {
        long answer = 1;
        v = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < goal[i]; j++) {
                if (!v[j]) {
                    answer += fac[N - i - 1];
                }
            }
            v[goal[i]] = true;
        }
        System.out.println(answer);
    }
}
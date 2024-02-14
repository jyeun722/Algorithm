import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static ArrayList<int[]> homes, chickens;
    static int[] index;

    static void comb(int cnt, int start) {
        if (cnt == M) {
            int sum = 0;
            for (int i = 0; i < homes.size(); i++) {
                int[] home = homes.get(i);
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    int[] chicken = chickens.get(index[j]);
                    int dis = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                    min = Math.min(min, dis);
                }
                sum += min;
            }
            result = Math.min(sum, result);
            return;
        }
        for (int i = start; i < chickens.size(); i++) {
            index[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        homes = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) homes.add(new int[]{i, j});
                else if (num == 2) chickens.add(new int[]{i, j});
            }
        }

        index = new int[M];
        comb(0, 0);

        System.out.println(result);
        br.close();
    }
}

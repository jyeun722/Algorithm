import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, C, result;
    static int[][] beehive;
    static int[] sub;
    static int[][] subSum;
    static ArrayList<int[]> benefits;

    static void select() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                benefits.add(new int[]{i, j, subSum[i][j]});
            }
        }

        Collections.sort(benefits, (i1, i2) -> i2[2] - i1[2]);
        int checkCnt = 0;
        boolean[][] visit = new boolean[N][N];
        for (int idx = 0; idx < benefits.size(); ) {
            int[] temp = benefits.remove(idx);
            int x = temp[0];
            int y = temp[1];

            boolean is = false;
            for (int j = y; j < y + M; j++) {
                if (visit[x][j]) {
                    is = true;
                    break;
                }
            }

            if (!is) {
                result += subSum[x][y];
                for (int j = y; j < y + M; j++) visit[x][j] = true;
                checkCnt++;
            }
            if (checkCnt == 2) break;
        }
    }

    static void comb(int cnt, int start, int c) {
        if (cnt == c) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) { // 각 행마다 가능한 크기 개수
                    int sumCnt = 0, sumBenefit = 0;
                    for (int k = 0; k < sub.length; k++) {
                        int num = beehive[i][j + sub[k]];
                        sumCnt += num;
                        sumBenefit += num * num;
                    }
                    if (sumCnt < C + 1 && sumBenefit > subSum[i][j]) {
                        subSum[i][j] = sumBenefit;
                    }
                }
            }
            return;
        }
        for (int i = start; i < M; i++) {
            sub[cnt] = i;
            comb(cnt + 1, i + 1, c);
        }
    }

    static void subStart() {
        benefits = new ArrayList<>();
        subSum = new int[N][N - M + 1];
        for (int c = 1; c < M + 1; c++) {
            sub = new int[c];
            comb(0, 0, c);
        }
        select();
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 벌통 크기
            M = Integer.parseInt(st.nextToken()); // 선택 가능한 벌통의 개수
            C = Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양
            result = 0;

            beehive = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    beehive[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            subStart();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
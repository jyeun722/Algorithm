import java.util.*;
class Solution {
    static int[] b = new int[5];
    static Set<Integer>[] set;
    static int answer = 0;
    
    static void comb(int cnt, int start, int n, int[] ans, int len) {
        if (cnt == 5) {
            int res = 0;
            for (int i = 0; i < len; i++) {
                int c = 0;
                for (int j = 0; j < 5; j++) {
                    if (set[i].contains(b[j])) c++;
                }
                if (ans[i] == c) res++;
            }
            if (res == len) answer++;
            return;
        }
        
        for (int i = start; i < n + 1; i++) {
            b[cnt] = i;
			comb(cnt + 1, i + 1, n, ans, len); 
        }
    }
    public int solution(int n, int[][] q, int[] ans) {
        set = new HashSet[q.length];
        for (int i = 0; i < q.length; i++) {
            set[i] = new HashSet<>();
            for (int num : q[i]) {
                set[i].add(num);
            }
        }
        
        comb(0, 1, n, ans, q.length);
        
        return answer;
    }
}
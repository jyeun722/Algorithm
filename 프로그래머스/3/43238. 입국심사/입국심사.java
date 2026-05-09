class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long max = 0;
        for (int i = 0; i < times.length; i++) {
            max = Math.max(times[i], max);
        }
        
        long lo = 0, hi = max * n;
        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            
            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                if (cnt >= n) break;
            }
            
            if (n <= cnt) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        answer = lo;
        
        return answer;
    }
}
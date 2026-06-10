import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long quarter = 0;
        
        long r1pow = (long) r1 * r1;
        long r2pow = (long) r2 * r2;
        
        for (int x = 1; x <= r2; x++) {
            long xpow = (long) x * x; 
            
            long maxY = (long) Math.sqrt(r2pow - xpow);
            
            long minY = 0;
            if (x < r1) {
                minY = (long) Math.ceil(Math.sqrt(r1pow - xpow));
            }
            
            quarter += (maxY - minY + 1);
        }
        
        return quarter * 4;
    }
}
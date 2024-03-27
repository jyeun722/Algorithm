import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dCnt = 0, pCnt = 0, dMax = -1, pMax = -1;
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) {
                dCnt += deliveries[i];
                dMax = i;
            }
            if (pickups[i] != 0) {
                pCnt += pickups[i];
                pMax = i;
            }
        } 
        
        int truck;
        while (dCnt != 0 || pCnt != 0) {
            answer += (dMax + 1) * 2;
            if (pMax > dMax) answer += (pMax - dMax) * 2;
            
            if (dCnt != 0) {
                truck = cap;
                for (int i = dMax; i >= 0;) {
                    dMax = i;
                    if (truck == 0) break; // 옮길 택배 없으면 멈춤
                    if (deliveries[i] > 0) { // 택배 배달
                        deliveries[i]--;
                        truck--;
                        dCnt--;
                    }

                    while (i >= 0 && deliveries[i] == 0) i--; // 택배 원하는 집 없으면 앞집으로 pass
                }
                
                if (dCnt == 0) dMax = 0;
            }
            
            if (pCnt != 0) {
                truck = 0;
                for (int i = pMax; i >= 0;) {
                    pMax = i;
                    if (truck == cap) break; // 택배 수거 다하면 멈춤
                    if (pickups[i] > 0) { // 택배 수거
                        pickups[i]--;
                        truck++;
                        pCnt--;
                    }

                    while (i >= 0 && pickups[i] == 0) i--; // 수거 원하는 집 없으면 앞집으로 pass
                }
                
                if (pCnt == 0) pMax = 0;
            }            
        }
        
        return answer;
    }
}
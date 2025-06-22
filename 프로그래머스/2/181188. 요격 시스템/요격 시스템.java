import java.util.*;
 
class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (i1, i2) -> i1[1] - i2[1]);
        int start = targets[0][0], end = targets[0][1];
        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] >= end) {
                start = targets[i][0];
                end = targets[i][1];
                answer++;
            } else if (targets[i][1] < end) {
                end = targets[i][1];
            }
        }
        
        return answer;
    }
}
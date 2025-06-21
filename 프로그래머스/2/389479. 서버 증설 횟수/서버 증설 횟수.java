import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        // players: 게임 이용자 수 배열
        // m: 서버 한대당 최대 이용자 수
        // k: 서버 한대가 운영가능한 시간
        
        int answer = 0;
        int[] server = new int[24];
 
        for (int i = 0; i < 24; i++) {
            if (players[i] >= (server[i] + 1) * m) {
                int n = players[i] / m; // 최소 서버 수
                int plus = n - server[i]; // 증설 수
                answer += plus;
                for (int j = 1; j < k && i + j < 24; j++) {
                    server[i + j] += plus;
                }
            }
        }
        
        return answer;
    }
}
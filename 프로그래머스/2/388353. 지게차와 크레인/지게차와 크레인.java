import java.util.*;

class Solution {
    static int[] dx = new int[] { -1, 0, 1, 0};
    static int[] dy = new int[] { 0, 1, 0, -1};
    
    static boolean out;
    static void outsideDfs(boolean[][] visit, int[][] arr, int x, int y, int i, int s, int len) {
        int nextX = 0, nextY = 0;
        for (int d = 0; d < 4; d++) {
            nextX = x + dx[d];
            nextY = y + dy[d];
            if (visit[nextX][nextY]) continue;
            visit[nextX][nextY] = true;
            
            if (arr[nextX][nextY] == -100) { // 바깥
                out = true;
                return;
            } else if (arr[nextX][nextY] < i - 99) {
                outsideDfs(visit, arr, nextX, nextY, i, s, len);
            } 
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int s = storage.length;
        int len = storage[0].length();
        int r = requests.length;
        int answer = s * len;
        
        int[][] arr = new int[s + 2][storage[0].length() + 2];
        for (int i = 0; i < s + 2; i++) Arrays.fill(arr[i], -100);
        for (int i = 1; i < s + 1; i++) {
            for (int j = 0; j < len; j++) {
                arr[i][j + 1] = storage[i - 1].charAt(j);
            }
            System.out.println(Arrays.toString(arr[i]));
        }
        
        for (int i = 0; i < r; i++) {
            int req = requests[i].charAt(0);
            
            if (requests[i].length() == 1) { // 지게차
                for (int j = 1; j < s + 1; j++) {
                    for (int k = 1; k < len + 1; k++) {
                        if (req != arr[j][k]) continue;
                        
                        out = false;
                        boolean[][] visit = new boolean[s + 2][len + 2];
                        visit[j][k] = true;
                        outsideDfs(visit, arr, j, k, i, s, len);
                        if (out) {
                            answer--;
                            arr[j][k] = i - 99;
                        }
                    }
                }
            } else { // 크레인(어디든)
                for (int j = 1; j < s + 1; j++) {
                    for (int k = 1; k < len + 1; k++) {
                        if (req != arr[j][k]) continue;
                        
                        arr[j][k] = i - 99;
                        answer--;
                    }
                }
            }
        }
        
        return answer;
    }
}
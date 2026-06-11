import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        
        boolean[] b = new boolean[message.length()];
        int len = spoiler_ranges.length;
        for (int i = 0; i < len; i++) {
            for (int j = spoiler_ranges[i][0]; j < spoiler_ranges[i][1] + 1; j++) {
                b[j] = true;
            }
        }
        
        boolean blind = false;
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> list = new HashSet<>();
        
        for (int i = 0; i < message.length(); i++) {
            char m = message.charAt(i);

            if (m == ' ') {
                String word = sb.toString();
                sb = new StringBuilder();
                
                if (blind) list.add(word);
                else map.put(word, map.getOrDefault(word, 0) + 1);
                
                blind = false;
            } else {
                if (b[i]) blind = true;
                sb.append(m);
            }
        }
        
        if (sb.length() > 0) {
            String word = sb.toString();
            if (blind) list.add(word);
            else map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        for (String str : list) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 1) {
                answer++;
            }
        }
        
        return answer;
    }
}
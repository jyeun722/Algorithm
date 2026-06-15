import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int[] video = Arrays.stream(video_len.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] cur = Arrays.stream(pos.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] os = Arrays.stream(op_start.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] oe = Arrays.stream(op_end.split(":")).mapToInt(Integer::parseInt).toArray();
        
        int videoInt = video[0] * 60 + video[1];
        int curInt = cur[0] * 60 + cur[1];
        int osInt = os[0] * 60 + os[1];
        int oeInt = oe[0] * 60 + oe[1];
        
        if (curInt >= osInt && curInt <= oeInt) curInt = oeInt;
        
        for (String command : commands) {
            if (command.equals("next")) {
                curInt += 10;
                if (curInt > videoInt) curInt = videoInt;
                else if (curInt >= osInt && curInt <= oeInt) {
                    curInt = oeInt;
                }
            } else {
                curInt -= 10;
                
                if (curInt < 0) curInt = 0;
                if (curInt >= osInt && curInt <= oeInt) {
                    curInt = oeInt;
                }
                
            }
        }
        
        String hour = String.valueOf(curInt / 60), min = String.valueOf(curInt % 60);
        if (hour.length() > 1) answer += hour + ":";
        else answer += "0" + hour + ":";
        
        if (min.length() > 1) answer += min;
        else answer += "0" + min;
        
        return answer;
    }
}
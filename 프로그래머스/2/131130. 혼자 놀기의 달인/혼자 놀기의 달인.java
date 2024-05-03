import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] cards) {
        int N = cards.length;
        boolean[] v = new boolean[N];
        List<Integer> group = new ArrayList<>();
        for(int i=0; i<N; i++){
            int cnt = 0;
            int idx = i;
            while(!v[idx]){
                v[idx]=true;
                idx=cards[idx]-1;
                cnt++;
            }
            group.add(cnt);
        }
        Collections.sort(group,(o1,o2)->o2-o1);
        return group.get(0)*group.get(1);
    }
}
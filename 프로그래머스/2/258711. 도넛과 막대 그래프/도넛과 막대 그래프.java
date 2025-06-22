import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        int[] answer = new int[4];

        for (int[] edge : edges) { // 각 in, out 갯수 저장
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        // out이 2개인 정점중에 in 간선이 없으면 생성한 정점, 있으면 8자 그래프
        for (int node : out.keySet()) {
            if (out.get(node) > 1) { 
                if (!in.containsKey(node)) {
                    answer[0] = node;
                } else {
                    answer[3] += 1;
                }
            }
        }

        // in 간선이 있는 정점 중에서 out 간선이 없으면 막대 그래프
        for (int node : in.keySet()) {
            if (!out.containsKey(node)) {
                answer[2] += 1;
            }
        }
        
        // 도넛 그래프 개수: 생성한 정점의 out 간선 개수 - 막대 그래프 개수 - 8자 그래프 개수
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        
        return answer;
    }
}
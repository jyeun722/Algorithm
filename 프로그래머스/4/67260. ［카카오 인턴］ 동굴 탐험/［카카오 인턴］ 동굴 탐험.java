import java.util.*;

class Solution {
    static int count;
    static void dfs(int n, List<Integer>[] rooms, Map<Integer, Integer> map) {
        // 방문 확인 배열
        boolean[] visit = new boolean[n];
        
        // 선방문 성공 대비 Map
        Map<Integer, Integer> beforeSuccess = new HashMap<>();
        
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(0);
        
        while(!que.isEmpty()) {
            int node = que.poll();
            
            // 이미 방문 성공한 노드의 경우 pass
            if (visit[node]) continue;
            
            if (map.containsKey(node)) {
                // 선행 노드가 있는 경우
                if (visit[map.get(node)]) {
                    // 선행 노드를 방문한 경우
                    for (int i = 0; i < rooms[node].size(); i++) {
                        que.offer(rooms[node].get(i));
                    }
                    visit[node] = true;
                    count++;
                } else {
                    // 선행 노드를 방문하지 않은 경우
                    beforeSuccess.put(map.get(node), node);
                }
            } else {
                // 선행 노드가 없는 경우
                for (int i = 0; i < rooms[node].size(); i++) {
                    que.offer(rooms[node].get(i));
                }
                visit[node] = true;
                count++;
                
                // 선행이었던 node가 방문 성공한다면, 그 뒤의 값도 넣어주기
                if (beforeSuccess.containsKey(node)) que.offer(beforeSuccess.get(node));
            }
        }
    }
    
    public boolean solution(int n, int[][] path, int[][] order) {
        count = 0; // 방문 성공 횟수 체크 변수
        
        // node에 대한 양방향 배열 생성
        List<Integer>[] rooms = new ArrayList[n];
        for (int i = 0; i < n; i++) rooms[i] = new ArrayList<>();
        
        // 선행 체크 Map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length; i++) {
            if (order[i][1] == 0) return false;
            map.put(order[i][1], order[i][0]); // 후행 노드, 선행 노드
        }
        
        for (int i = 0; i < n - 1; i++) {
            rooms[path[i][0]].add(path[i][1]);
            rooms[path[i][1]].add(path[i][0]);
        }
        dfs(n, rooms, map);
        
        return count == n; // 총 갯수와 동일하다면 true
    }
}
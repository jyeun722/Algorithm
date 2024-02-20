import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K; // 행, 열, 배양 시간
    static int[][] area;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    // 0: x, 1: y, 2: 생명력, 3: 번식까지 남은 시간, 4: 죽을때까지 남은 시간
    
    // timeStart -> multiPly -> apply
    
    static void apply(List<int[]> cells, PriorityQueue<int[]> addCells) {
        while (!addCells.isEmpty()) {
        	int[] cell = addCells.poll();
            if (area[cell[0]][cell[1]] < cell[2]) {
                area[cell[0]][cell[1]] = cell[2];
                cells.add(cell);
            } // 생명력이 큰 세포들부터 추가 세포와 area에 등록
        }
    }

    static void multiPly(List<int[]> cells) {
        // 사방 검색해서 새로이 추가될 세포들
        // 생명력이 큰 세포들부터 입력되도록 정렬
        PriorityQueue<int[]> addCells = new PriorityQueue<>(
        		(i1, i2) -> Integer.compare(i2[2], i1[2]));

        for (int c = 0; c < cells.size(); c++) {
            int[] cell = cells.get(c);
            if (cell[3] == 0) { // 번식 시간이 0일 때 -> 번식 시작
                for (int i = 0; i < dx.length; i++) { // 사방 검색 시작
                    int nextX = cell[0] + dx[i];
                    int nextY = cell[1] + dy[i];

                    if (area[nextX][nextY] != 0) continue; // 이미 세포가 있는 구역 패스

                    addCells.offer(new int[]{nextX, nextY, cell[2], cell[2], cell[2]});
                }
                cell[4]--; // 번식이 된 후이므로 죽을때까지 남은 시간 감소
            } else {
                cell[3]--; // 번식 시작이 안된 세포이므로 번식까지 남은 시간 감소
            }
            cells.set(c, cell); // 세포들 다시 세팅
        }

        Collections.sort(cells, (i1, i2) -> Integer.compare(i1[4], i2[4]));
        // 즉을 때까지 남은시간이 0부터 되도록 정렬
        
        for (int c = 0; c < cells.size(); c++) {
            int[] cell = cells.get(c);
            if (cell[4] == 0) { // 죽는 시간이 0인 세포들만 삭제
                cells.remove(c--);
            } else break;
        }

        apply(cells, addCells);
    }

    // 시간 시작 함수
    static int timeStart(List<int[]> cells) {
        for (int i = 1; i < K + 1; i++) { // 1초부터 K초까지 시작
            multiPly(cells);
        }

        return cells.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 행
            M = Integer.parseInt(st.nextToken()); // 열
            K = Integer.parseInt(st.nextToken()); // 배양 시간

            List<int[]> cells = new ArrayList<>();

            area = new int[N + 2 * K][M + 2 * K]; 
            // K시간동안 배양하기 때문에 사방에 K만큼 추가
            for (int i = K; i < N + K; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = K; j < M + K; j++) {
                    int aliveTime = Integer.parseInt(st.nextToken());
                    area[i][j] = aliveTime; 

                    if (aliveTime != 0) { // 생명력이 있으면 살아는 세포 목록에 추가
                        int[] cell = new int[]{i, j, aliveTime, aliveTime, aliveTime};
                        cells.add(cell);
                    }
                }
            }

            int result = timeStart(cells);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
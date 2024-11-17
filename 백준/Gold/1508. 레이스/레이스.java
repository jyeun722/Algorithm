import java.io.*;
import java.util.*;

public class Main {
    static String oneCnt(int[] arr, int mid, int M, int K) {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        int cnt = 1; // 맨 처음에는 배치한다고 생각하고 시작 // 사전 순 늦는순
        int before = arr[0]; // 배치한 이전 것을 담아놓음, i-1을 하지 않는 이유는 i-1이 꼭 배치된게 아닐 수 있음 주의!

        for (int i = 1; i < K; i++) {
            if (cnt == M) {
                // 이미 1의 수가 심판수와 같은 경우, 심판 더 세울필요 없으니까 0 추가
                sb.append("0");
            } else {
                // 심판을 더 세워야하는 경우
                if (arr[i] - before >= mid) {
                    // 현재 위치에서 이전 심판 위치(before)까지의 거리가 최소 길이(mid)보다 큰 경우
                    sb.append("1");
                    cnt++;
                    before = arr[i]; // 카운트와 이전 위치 갱신
                } else {
                    // 최소 길이보다 작으면 설치 불가 -> 0 추가
                    sb.append("0");
                }
            }
        }

        if (cnt == M) return sb.toString(); // 카운트 다 채운경우만 결과 리턴
        return ""; // 아닌경우 그냥 빈배열
    }

    static String binarySearch(int[] arr, int N, int M, int K) { // 트랙 길이, 심판 수, 위치 개수
        int lo = 0;
        int hi = N;
        String ans = "";

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            String ret = oneCnt(arr, mid, M, K);

            // 정렬된 숫자가 나온 경우, 충분히 다들 mid보다 큰 것이기 때문에, mid를 더 키워도 됨 -> lo 증가, ans 갱신 
            if (!ret.equals("")) {
                lo = mid + 1;
                ans = ret;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 레이스 트랙의 길이
        int M = Integer.parseInt(st.nextToken()); // 심판 수
        int K = Integer.parseInt(st.nextToken()); // 심판이 있을 수 있는 K개의 위치

        int[] arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        String answer = binarySearch(arr, N, M, K);
        System.out.println(answer);

        br.close();
    }
}
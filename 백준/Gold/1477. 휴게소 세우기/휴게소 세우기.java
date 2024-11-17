import java.io.*;
import java.util.*;

public class Main {
    static int distance(int[] arr, int mid, int N) {
        int cnt = 0;
        for (int i = 1; i < N + 2; i++) {
            // arr[i] - arr[i - 1] - 1
            // i번과 i - 1번 사이에서 지을 수 있는 휴게소의 개수
            // 2, 10 일 경우, 3 ~ 9 이므로 7 -> 10 - 2 - 1 = 7

            // 길이 / mid => 7 / 2 = 3개, 7 / 10 = 10개
            cnt += (arr[i] - arr[i - 1] - 1) / mid;

            // 즉, 각 휴게소 사이에서 mid의 길이의 간격을 두었을 때 지을 수 있는 휴게소의 갯수 총 합 반환
        }
        return cnt;
    }

    static int binarySearch(int[] arr, int N, int M, int L) {
        int lo = 1; // 휴게소 지을 수 있는 최소 위치
        int hi = L - 1; // 최대 위치

        while (lo <= hi) {
            int mid = (lo + hi) / 2; // mid는 거리로
            if (distance(arr, mid, N) > M) { // lowerbound, 키랑 같은 값도 포함이므로
                // 지을 수 있는 휴게소의 갯수가 M보다 클 때, mid의 길이를 늘려야함 => lo를 크게 만듦: mid 커짐
                lo = mid + 1;
            } else {
                // 반대로 mid 줄임 => hi를 크게 만듦
                hi = mid - 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 휴게소의 개수
        int M = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소의 개수
        int L = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        int[] arr = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N + 1] = L; // 맨 뒤 넣어줌

        Arrays.sort(arr);

        int answer = binarySearch(arr, N, M, L);
        System.out.println(answer);

        br.close();
    }
}
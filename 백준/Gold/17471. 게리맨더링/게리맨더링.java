import java.util.*;
import java.io.*;

public class Main {
    static int N, sum;
    static int result = Integer.MAX_VALUE;
    static int[] person;
    static List<Integer>[] link;

    static List<List<Integer>> record = new ArrayList<>();

    static void countMinus(List<Integer> one) {
        int arrSum = 0;
        for (int i = 0; i < one.size(); i++) {
            arrSum += person[one.get(i)];
        }
        int minus = Math.abs(sum - arrSum * 2);
        if (minus < result) result = minus;
    }

    static boolean checkConnect(List<Integer> one, List<Integer> two) {
        List<Integer> checkArr = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();
        que.add(two.get(0));

        while (!que.isEmpty()) {
            int node = que.poll();
            List<Integer> nodeList = new ArrayList<>(link[node]);
            nodeList.add(node);
            for (int i = 0; i < nodeList.size(); i++) {
                int num = nodeList.get(i);
                if (one.contains(num)) continue;
                if (!checkArr.contains(num)) {
                    checkArr.add(num);
                    que.add(num);
                }
            }
        }
        return checkArr.size() == two.size();
    }

    static boolean equal(List<Integer> arr) {
        for (List<Integer> l : record) {
            if (l.size() == arr.size()) {
                int cnt = 0;
                for (int a : arr) {
                    if (l.contains(a)) {
                        cnt++;
                    }
                }
                if (cnt == l.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    static void com(List<Integer> areaA, int[] arr, boolean[] visit, int start, int r) {
        if (r == 0) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visit[i]) {
                    temp.add(arr[i]);
                }
            }
            List<Integer> areaB = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (!temp.contains(i)) areaB.add(i);
            }

            boolean go = equal(temp);
            if (go && checkConnect(areaB, temp) && checkConnect(temp, areaB)) {
                countMinus(temp);
            }
        }
        for (int i = start; i < N; i++) {
            if (areaA.contains(i)) continue;
            visit[i] = true;
            com(areaA, arr, visit, i + 1, r - 1);
            visit[i] = false;
        }
    }

    static void divide() {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = i;

        for (int i = 0; i < N; i++) {
            boolean[] visit = new boolean[N];
            List<Integer> areaA = new ArrayList<>();
            visit[i] = true;
            areaA.add(i);
            for (int j = 0; j < N - 1; j++) {
                com(areaA, arr, visit, 0, j);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        person = new int[N]; // 각 도시별 인구 수 입력
        sum = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            person[i] = num;
            sum += num;
        }

        link = new ArrayList[N];
        for (int i = 0; i < N; i++) link[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                link[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        divide();
        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);

        br.close();
    }
}
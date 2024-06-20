import java.io.*;
import java.util.*;

public class Main {
    static Node root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<String, Integer> map = new HashMap<>();
        root = new Node();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);

            int cnt = map.get(str);
            if (cnt == 1) {
                // 처음 들어온 단어
                insert(str, sb);
            } else {
                // 두 번 이상 들어온 단어
                sb.append(str + cnt).append("\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void insert(String str, StringBuilder sb) {
        Node node = root;
        boolean prefixAdd = false;

        for (int i = 0; i < str.length(); i++) {
            // key 값에 해당하는 value가 존재하면 가져와서 사용, 없으면 새로 생성
            node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
            node.count++;

            if (!prefixAdd) {
                sb.append(str.charAt(i));
                if (node.count == 1) {
                    prefixAdd = true;
                }
            }
        }

        sb.append("\n");
    }

    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        int count; // 여기까지 겹치는 단어의 갯수
    }
}

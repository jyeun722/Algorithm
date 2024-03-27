import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int vertex, value;
		
		public Node (int vertex, int value) {
			this.vertex = vertex;
			this.value = value;
		}
	}
	
    static int V, result, far;
    static List<Node>[] tree;
    static boolean[] visit;

    static void dfs(int ver, int value) {
    	if (result < value) {
    		result = value;
    		far = ver;
    	}
        
        for (int i = 0; i < tree[ver].size(); i++) {
        	Node node = tree[ver].get(i);
            int v = node.vertex;
            if (!visit[v]) {
                visit[v] = true;
                dfs(v, node.value + value);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());

        tree = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            int end = Integer.parseInt(st.nextToken());
            while (end != -1) {
                int dis = Integer.parseInt(st.nextToken());
                tree[start].add(new Node(end, dis));
                end = Integer.parseInt(st.nextToken());
            }
        }

        far = -1;
        
        visit = new boolean[V + 1];
        visit[1] = true;
        dfs(1, 0);
        
        visit = new boolean[V + 1];
        visit[far] = true;
        dfs(far, 0);
        
        System.out.println(result);
        br.close();
    }
}

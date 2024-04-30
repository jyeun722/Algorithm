import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static char[][] g;
	static boolean[][][] v;
	static int[] dr= {1,-1,-1,1,  0,0,-1,1, 0};
	static int[] dc= {1,1,-1,-1,  -1,1,0,0, 0};
	static int Edist,goalR,goalC,ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		g=new char[N][N];
		v=new boolean[N][N][3];
		List<int[]> B = new ArrayList<>();
		List<int[]> E = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				g[i][j]=str.charAt(j);
				if(g[i][j]=='B')
					B.add(new int[] {i,j}); // 무조건 가운대가 get(1)
				else if(g[i][j]=='E')
					E.add(new int[] {i,j});
			}
		}
		int[] start = B.get(1);
		goalR = E.get(1)[0]; // 무조건 가운대가 get(1)
		goalC = E.get(1)[1]; // 무조건 가운대가 get(1)
		Edist = isDist(E);
		bfs(start[0],start[1],isDist(B));
		System.out.println(ans);
	}
	
	static void bfs(int r, int c, int dist) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c,dist,0});
		v[r][c][dist+1]=true;
		while(!q.isEmpty()) {
			int[] now=q.poll();
			r = now[0];
			c = now[1];
			dist = now[2]; // dist 1이면 세로 dist -1이면 가로
			int cnt = now[3];
			if(r==goalR && c==goalC && dist==Edist) {
				ans=cnt;
				return;
			}
			
			for(int d=4; d<=8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isValid(nr, nc)) {
					if(d==8) {
						if(CanTurn(nr, nc) && !v[nr][nc][dist*-1+1]) {
							dist*=-1;
							q.offer(new int[] {nr,nc,dist,cnt+1});
							v[nr][nc][dist+1]=true;
						}
					}else if(dist==1 && !v[nr][nc][dist+1]){
						if(isValid(nr+1, nc) && isValid(nr-1, nc)) {
							q.offer(new int[] {nr,nc,dist,cnt+1});
							v[nr][nc][dist+1]=true;
						}
					}else if(dist==-1 && !v[nr][nc][dist+1]) {
						if(isValid(nr, nc+1) && isValid(nr, nc-1)) {
							q.offer(new int[] {nr,nc,dist,cnt+1});
							v[nr][nc][dist+1]=true;
						}
					}
				}
			}
		}
	}
	
	static boolean CanTurn(int r, int c) {
		for(int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!isValid(nr, nc)) return false;
		}
		return true;
	}
	
	static boolean isValid(int nr, int nc) {
		return 0<=nr&&nr<N && 0<=nc&&nc<N && g[nr][nc]!='1';
	}
	
	static int isDist(List<int[]> list) {
		if(list.get(0)[0]-list.get(1)[0] == 0) return -1; //가로면
		else return 1; //세로면
	}

}
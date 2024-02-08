import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] arr;
    
    static void roll(int oper) {
    	switch (oper) {
    	case 1:
    		for (int i = 0; i < N / 2; i++) {
    			int[] temp = Arrays.copyOf(arr[i], M);
    			arr[i] = Arrays.copyOf(arr[N - i - 1], M);
    			arr[N - i - 1] = Arrays.copyOf(temp, M);
    		}
    		break;
    	case 2:
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < M / 2; j++) {
    				int temp = arr[i][j];
    				arr[i][j] = arr[i][M - 1 - j];
    				arr[i][M - 1 - j] = temp;
    			}
    		}
    		break;
    	case 3:
    		int[][] temp3 = new int[M][N];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < M; j++) {
    				temp3[j][N - 1 - i] = arr[i][j];
    			}
    		}
    		
    		int tempSize3 = N;
    		N = M;
    		M = tempSize3;
    		
    		arr = temp3;
    		break;
    	case 4:
    		int[][] temp4 = new int[M][N];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < M; j++) {
    				temp4[M - j - 1][i] = arr[i][j];
    			}
    		}
    		
    		int tempSize4 = N;
    		N = M;
    		M = tempSize4;
    		
    		arr = temp4;
    		break;
    	case 5:
    		int[][] xy = {{0, 0}, {0, M / 2}, {N / 2, M / 2}, {N / 2, 0}};
    		int[][] xy2 = {{0, M / 2}, {N / 2, M / 2}, {N / 2, 0}, {0, 0}};
    		int[][] temp5 = new int[N][M];
    		for (int c = 0; c < 4; c++) {
    			for (int i = xy[c][0], j = xy2[c][0]; i < xy[c][0] + N / 2; i++) {
    				System.arraycopy(arr[i], xy[c][1], temp5[j++], xy2[c][1], M / 2);
    			}
    		}
    		arr = temp5;
    		break;
    	case 6:
    		int[][] xy3 = {{0, 0}, {0, M / 2}, {N / 2, M / 2}, {N / 2, 0}};
    		int[][] xy4 = {{N / 2, 0}, {0, 0}, {0, M / 2}, {N / 2, M / 2}};
    		int[][] temp6 = new int[N][M];
    		for (int c = 0; c < 4; c++) {
    			for (int i = xy3[c][0], j = xy4[c][0]; i < xy3[c][0] + N / 2; i++) {
    				System.arraycopy(arr[i], xy3[c][1], temp6[j++], xy4[c][1], M / 2);
    			}
    		}
    		arr = temp6;
    		break;
    	}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()); // 연산 수

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] operation = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) operation[i] = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < R; i++) roll(operation[i]);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

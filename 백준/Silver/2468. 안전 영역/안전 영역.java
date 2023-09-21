import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int maxHeight;
	static int ans;
	static int[] dx = { -1, 0, 1, 0};
	static int[] dy = { 0, -1, 0, 1};
	static boolean[][] visited;
	static int[][] map;
	
	// 높이가 h일 때, 안전 영역의 개수를 확인하는 메서드
	public static int bfs(int h) {
		int cnt = 0; // 안전 영역의 개수
		
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= h) visited[i][j] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					Queue<Point> q = new LinkedList<Point>();
					q.add(new Point(i, j));
					
					while (!q.isEmpty()) {
						Point p = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int nx = p.x + dx[d];
							int ny = p.y + dy[d];
							
							if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
							
							visited[nx][ny] = true;
							q.add(new Point(nx, ny));
						}
					}
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		} // end input
		
		for (int i = 0; i <= maxHeight; i++) {
			ans = Math.max(ans, bfs(i));
		}
		
		System.out.println(ans);
	}
}
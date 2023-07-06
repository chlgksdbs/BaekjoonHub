import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		
		int i, j, k; // 3차원 배열에서의 층, 행, 열 좌표값
		int time; // 현재 이동 시간

		public Edge(int i, int j, int k, int time) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
			this.time = time;
		}
	}
	
	static int L, R, C; // 층, 행, 열
	static int si, sj, sk; // 시작점의 층, 행, 열
	static int ei, ej, ek; // 끝점의 층, 행, 열
	static int ans; // 상범 빌딩을 탈출하는 데에 필요한 최단 시간
	
	// 이동하는 좌표값 (동, 서, 남, 북, 상, 하 순으로)
	static int[] dx = { 0, 0, 1, -1, 0, 0 };
	static int[] dy = { 1, -1, 0, 0, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	
	static boolean[][][] visited; // 방문 처리 배열
	static char[][][] map; // 상범 빌딩의 구조
	
	// 3차원 배열을 가지고 bfs를 수행하는 메서드
	public static void bfs() {
		
		Queue<Edge> q = new LinkedList<Edge>();
		
		// 시작점의 위치를 queue에 삽입 후, 방문 처리
		q.add(new Edge(si, sj, sk, 0));
		visited[si][sj][sk] = true;
		
		// queue가 빌 때까지 수행
		while (!q.isEmpty()) {
			Edge e = q.poll();
			
			// 도착 지점에 도달한 경우, 값을 갱신
			if (e.i == ei && e.j == ej && e.k == ek) {
				if (ans == -1) {
					ans = e.time;
				}
				else {
					ans = Math.min(ans, e.time);
				}
			}
			
			// 6방향으로의 이동
			for (int i = 0; i < 6; i++) {
				int ni = e.i + dz[i];
				int nj = e.j + dx[i];
				int nk = e.k + dy[i];
				int ntime = e.time + 1;
				
				// (1) 범위를 벗어나는 경우, 무시
				if (ni < 0 || nj < 0 || nk < 0 || ni >= L || nj >= R || nk >= C) continue;
				
				// (2) 벽인 경우, 무시
				if (map[ni][nj][nk] == '#') continue;
				
				// (3) 방문처리가 안된 경우, 수행
				if (!visited[ni][nj][nk]) {
					q.add(new Edge(ni, nj, nk, ntime));
					visited[ni][nj][nk] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 종료조건이 입력될 때까지 반복 수행
		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if (L == 0 && R == 0 && C == 0) break; // 종료조건
			
			visited = new boolean[L][R][C];
			map = new char[L][R][C];
			ans = -1;
			
			// 층의 개수만큼 반복문 수행
			for (int i = 0; i < L; i++) {
				// 행의 개수만큼 반복문 수행
				for (int j = 0; j < R; j++) {
					map[i][j] = br.readLine().toCharArray();
					// 열의 개수만큼 반복문 수행 (S, E 지점 찾기)
					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == 'S') {
							si = i;
							sj = j;
							sk = k;
						}
						
						if (map[i][j][k] == 'E') {
							ei = i;
							ej = j;
							ek = k;
						}
					}
				}
				br.readLine();
			} // end input
			
			bfs(); // 3차원 배열의 bfs 함수 실행
			
			// 탈출 못한 경우
			if (ans == -1) {
				System.out.println("Trapped!");
			}
			// 탈출 한 경우
			else {
				System.out.println("Escaped in " + ans + " minute(s).");
			}
		}
	}
}
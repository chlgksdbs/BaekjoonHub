import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean ans; // 감시를 피할 수 있는지 체크하는 값
	static int N;
	static boolean[] visited; // blanks에 인덱스 값에 해당하는 부분이 처리됐는지 체크하는 배열
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static char[][] map; // 복도의 정보
	static List<Point> teachers; // 선생님에 대한 좌표 정보
	static List<Point> blanks; // 빈칸에 대한 좌표 정보
	
	// 디버깅을 위한 메서드
	public static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// comb에서 고른 경우의 수에 설치된 장애물을 통해 감시 상태를 체크하는 메서드
	public static boolean isPossible() {
		for (int i = 0; i < teachers.size(); i++) {
			Point p = teachers.get(i);
			
			// 4방향 탐색
			for (int j = 0; j < 4; j++) {
				int nx = p.x + dx[j];
				int ny = p.y + dy[j];
				
				while (true) {
					// 탐색 범위를 벗어나는 경우 종료
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 'O') break;
					
					// 학생을 마주치는 경우, 불가능한 경우로 종료
					if (map[nx][ny] == 'S') return false;
					
					// 앞으로 전진
					nx += dx[j];
					ny += dy[j];
				}
			}
		}
		
		return true;
	}
	
	// 빈 칸에 장애물을 설치해보며 경우의 수를 체크하는 메서드
	public static void comb(int cnt, int idx) {
		// 3개의 장애물을 설치한 경우
		if (cnt == 3) {
			if (isPossible()) ans = true;
			return;
		}
		
		for (int i = idx; i < blanks.size() && !ans; i++) {
			if (!visited[i]) {
				Point p = blanks.get(i);
				
				visited[i] = true;
				map[p.x][p.y] = 'O';
				comb(cnt + 1, idx + 1);
				map[p.x][p.y] = 'X';
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teachers = new ArrayList<Point>();
		blanks = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				
				// 현재 칸이 빈칸이 경우, blanks 리스트에 삽입
				if (map[i][j] == 'X') blanks.add(new Point(i, j));
				// 현재 칸이 선생님인 경우, teachers 리스트에 삽입
				else if (map[i][j] == 'T') teachers.add(new Point(i, j));
			}
		} // end input
		
		visited = new boolean[blanks.size()];
		comb(0, 0);
		
		System.out.println(ans == true ? "YES" : "NO");
	}
}
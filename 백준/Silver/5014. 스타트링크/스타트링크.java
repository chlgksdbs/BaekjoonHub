import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int F, S, G, U, D;
	static int[] stairs;
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		stairs[S] = 0;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			// Up
			if ((v + U <= F) && stairs[v + U] == Integer.MAX_VALUE) {
				stairs[v + U] = Math.min(stairs[v + U], stairs[v] + 1);
				q.add(v + U);
			}
			
			// Down
			if ((v - D > 0) && stairs[v - D] == Integer.MAX_VALUE) {
				stairs[v - D] = Math.min(stairs[v - D], stairs[v] + 1);
				q.add(v - D);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		stairs = new int[F + 1];
		Arrays.fill(stairs, Integer.MAX_VALUE);
		
		bfs();
		
		if (stairs[G] == Integer.MAX_VALUE) {
			System.out.println("use the stairs");
		} else {
			System.out.println(stairs[G]);
		}
	}
}
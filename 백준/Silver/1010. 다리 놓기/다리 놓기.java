import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int T; // 테스트 케이스의 개수
	static int M, N; // 강의 서쪽, 동쪽 사이트의 개수
	
	static int[][] dp; // 이항 계수를 구하기 위한 dp 테이블
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			M = sc.nextInt();
			N = sc.nextInt();
			
			dp = new int[N + 1][M + 1];
			
			for (int i = 1; i <= N; i++) {
				int endLoop = Math.min(i, M); // 항상 M보다 작거나 같음
				
				for (int j = 0; j <= endLoop; j++) {
					if (j == 0 || i == j) dp[i][j] = 1; // 기저 조건
					else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
			
			sb.append(dp[N][M] + "\n");
		}
		
		System.out.println(sb);
	}
}
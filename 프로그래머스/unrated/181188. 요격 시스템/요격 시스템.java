import java.util.*;

class Solution {
    
    static int answer; // 요격 미사일 수의 최솟값
    static boolean[] visited; // 해당 미사일이 처리됐는지 체크하는 배열
    
    public int solution(int[][] targets) {
        
        visited = new boolean[targets.length];
        
        Arrays.sort(targets, (o1, o2) -> {
           return o1[0] - o2[0]; // 출발 지점의 오름차순으로 정렬 
        });
        
        for (int i = 0; i < targets.length; i++) {
            // 현재 미사일이 처리되지 않은 경우 수행
            if (!visited[i]) {
                int idx = i;
                visited[idx++] = true;
                int start = targets[i][0]; // 시작점
                int end = targets[i][1]; // 끝점
                
                // 현재 미사일의 범위에 포함되어 있는 경우, 방문 처리
                while (idx < targets.length) {
                    if (start <= targets[idx][0] && targets[idx][0] < end) {
                        // 끝점이 더 작은 범위인 경우, 갱신
                        if (targets[idx][1] < end) end = targets[idx][1];
                        visited[idx++] = true;
                        continue;
                    }
                    break;
                }
                
                answer++; // count 증가
            }
        }
        
        return answer;
    }
}
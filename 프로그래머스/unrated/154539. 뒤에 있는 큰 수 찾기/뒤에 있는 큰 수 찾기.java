import java.util.*;
import java.io.*;

class Solution {
    
    static Stack<Integer> stack; // 뒤에서 부터 탐색하며, 상태를 기록하는 stack 자료구조
    
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        
        int[] answer = new int[len];
        stack = new Stack<>();
        
        // 뒤에서부터 탐색
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.empty()) {
                // 현재 수보다 stack의 top이 더 큰 경우
                if (numbers[i] < stack.peek()) {
                    answer[i] = stack.peek();
                    break;
                } else {
                    stack.pop();
                }
            }
            // stack이 빈 경우, 현재 수가 가장 큰 수이므로 -1을 저장
            if (stack.empty()) {
                answer[i] = -1;
            }
            stack.push(numbers[i]); // 현재 수를 stack에 추가
        }
        
        return answer;
    }
}
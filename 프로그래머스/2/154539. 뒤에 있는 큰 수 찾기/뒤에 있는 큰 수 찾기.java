import java.util.Stack;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        int n = numbers.length;
        answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            answer[i] = -1;
            
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            
            stack.push(i);
        }

        return answer;
    }
}
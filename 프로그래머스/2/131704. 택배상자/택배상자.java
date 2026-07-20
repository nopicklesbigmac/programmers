import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> subBelt = new Stack<>();
        int currentBox = 1;
        int n = order.length;

        for (int i = 0; i < n; i++) {
            int target = order[i];

            while (currentBox < target) {
                subBelt.push(currentBox++);
            }

            if (currentBox == target) {
                answer++;
                currentBox++;
            } else if (!subBelt.isEmpty() && subBelt.peek() == target) {
                subBelt.pop();
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }
}
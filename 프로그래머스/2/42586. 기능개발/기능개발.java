import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> daysLeft = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            daysLeft.add((int) Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }

        List<Integer> result = new ArrayList<>();
        while (!daysLeft.isEmpty()) {
            int currentMax = daysLeft.poll();
            int count = 1;

            while (!daysLeft.isEmpty() && daysLeft.peek() <= currentMax) {
                daysLeft.poll();
                count++;
            }
            result.add(count);
        }

        answer = result.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}
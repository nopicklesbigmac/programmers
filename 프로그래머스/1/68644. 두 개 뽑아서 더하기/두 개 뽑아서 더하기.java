import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        Set<Integer> resultSet = new HashSet<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                resultSet.add(numbers[i] + numbers[j]);
            }
        }
        answer = new int[resultSet.size()];
        int idx = 0;
        for (int num : resultSet) {
            answer[idx++] = num;
        }

        Arrays.sort(answer);
        return answer;
    }
}
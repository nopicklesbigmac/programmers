import java.util.Arrays;
class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        Arrays.sort(array);
        
        answer = array[0];
        int min = Math.abs(n - array[0]);
        for (int i = 1; i < array.length; i++) {
            int current = Math.abs(n - array[i]);
            if (current < min) {
                min = current;
                answer = array[i];
            }
        }
        return answer;
    }
}
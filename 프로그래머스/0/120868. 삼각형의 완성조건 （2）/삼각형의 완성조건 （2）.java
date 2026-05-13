import java.util.Arrays;
class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        Arrays.sort(sides);
        int min = sides[0];
        int max = sides[1];
        answer = (min * 2) - 1;
        return answer;
    }
}
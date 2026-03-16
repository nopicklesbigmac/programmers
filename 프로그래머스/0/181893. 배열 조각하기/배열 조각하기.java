import java.util.*;
class Solution {
    public int[] solution(int[] arr, int[] query) {
        int[] answer = {};
        int start = 0;
        int end = arr.length - 1;

        for (int i = 0; i < query.length; i++) {
            if (i % 2 == 0) {
                end = start + query[i];
            } else {
                start = start + query[i];
            }
        }
        answer = Arrays.copyOfRange(arr, start, end + 1);
        return answer;
    }
}
import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        List<Integer> indexList = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                indexList.add(i);
            }
        }
        if (indexList.isEmpty()) {
            answer = new int[]{-1};
        } else {
            int start = indexList.get(0);
            int end = indexList.get(indexList.size() - 1);
            answer = new int[end - start + 1];
            int idx = 0;
            for (int i = start; i <= end; i++) {
                answer[idx++] = arr[i];
            }
        }
        return answer;
    }
}
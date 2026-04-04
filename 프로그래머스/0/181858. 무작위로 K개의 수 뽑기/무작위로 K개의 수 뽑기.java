import java.util.*;
class Solution {
    public int[] solution(int[] arr, int k) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (!set.contains(num)) {
                set.add(num);
                list.add(num);
            }
            if (list.size() == k) break;
        }
        answer = new int[k];
        Arrays.fill(answer, -1);
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int n, int[] numlist) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        
        for (int num : numlist) {
            if (num % n == 0) { 
                list.add(num);
            }
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
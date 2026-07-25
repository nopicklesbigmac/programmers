import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = {};
        answer = new int[n];
        List<Integer> list = new ArrayList<>();
        long factorial = 1;
        
        for (int i = 1; i <= n; i++) {
            list.add(i);
            if (i < n) {
                factorial *= i;
            }
        }
        
        k--;
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            int selected = (int) (k / factorial);
            answer[idx++] = list.get(selected);
            list.remove(selected);
            
            k %= factorial;
            if (n - 1 - i > 0) {
                factorial /= (n - 1 - i);
            }
        }
        
        return answer;
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int l, int r) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        for (int i = 1; ; i++) {
            String binaryString = Integer.toBinaryString(i);
            long num = Long.parseLong(binaryString) * 5;
            if (num > r) break;
            if (num >= l) {
                list.add((int) num);
            }
        }
        
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
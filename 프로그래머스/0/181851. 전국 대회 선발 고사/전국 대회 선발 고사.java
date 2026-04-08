import java.util.*;
class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        List<int[]> list = new ArrayList<>();
        
        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                list.add(new int[]{rank[i], i});
            }
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        int a = list.get(0)[1];
        int b = list.get(1)[1];
        int c = list.get(2)[1];
        answer = 10000 * a + 100 * b + c;
        return answer;
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> groupSizes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int current = i;
                int count = 0;
                while (!visited[current]) {
                    visited[current] = true;
                    current = cards[current] - 1;
                    count++;
                }
                groupSizes.add(count);
            }
        }

        if (groupSizes.size() < 2) {
            return 0;
        }

        groupSizes.sort(Collections.reverseOrder());

        return groupSizes.get(0) * groupSizes.get(1);
    }
}
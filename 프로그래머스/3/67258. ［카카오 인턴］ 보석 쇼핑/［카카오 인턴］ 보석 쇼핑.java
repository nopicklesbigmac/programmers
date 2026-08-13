import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};

    Set<String> set = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int bestLeft = 0;
        int bestRight = gems.length - 1;

        while (right < gems.length) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            right++;

            while (map.size() == set.size()) {
                if (right - left < bestRight - bestLeft + 1) {
                    bestLeft = left;
                    bestRight = right - 1;
                }

                map.put(gems[left], map.get(gems[left]) - 1);

                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }

                left++;
            }
        }

        answer = new int[]{bestLeft + 1, bestRight + 1};
        return answer;
    }
}
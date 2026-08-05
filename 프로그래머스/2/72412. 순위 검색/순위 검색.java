import java.util.*;
class Solution {
    Map<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        map = new HashMap<>();
        for (String str : info) {
            String[] split = str.split(" ");
            makeComb(split, 0, "", Integer.parseInt(split[4]));
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll("and ", "");
            String[] split = q.split(" ");
            
            String key = split[0] + split[1] + split[2] + split[3];
            int score = Integer.parseInt(split[4]);

            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                answer[i] = binarySearch(list, score);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private void makeComb(String[] split, int depth, String curr, int score) {
        if (depth == 4) {
            map.putIfAbsent(curr, new ArrayList<>());
            map.get(curr).add(score);
            return;
        }
        makeComb(split, depth + 1, curr + split[depth], score);
        makeComb(split, depth + 1, curr + "-", score);
    }

    private int binarySearch(List<Integer> list, int score) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= score) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return list.size() - left;
    }
}
import java.util.HashMap;
import java.util.Map;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int x = routes.length;
        int m = routes[0].length;
        Map<Integer, Map<String, Integer>> timeMap = new HashMap<>();

        for (int i = 0; i < x; i++) {
            int time = 0;
            int r = points[routes[i][0] - 1][0];
            int c = points[routes[i][0] - 1][1];
            addPosition(timeMap, time, r, c);

            for (int j = 1; j < m; j++) {
                int nextR = points[routes[i][j] - 1][0];
                int nextC = points[routes[i][j] - 1][1];
                while (r != nextR) {
                    r += (nextR > r) ? 1 : -1;
                    time++;
                    addPosition(timeMap, time, r, c);
                }
                while (c != nextC) {
                    c += (nextC > c) ? 1 : -1;
                    time++;
                    addPosition(timeMap, time, r, c);
                }
            }
        }
            for (int time : timeMap.keySet()) {
            Map<String, Integer> posMap = timeMap.get(time);
            for (String pos : posMap.keySet()) {
                if (posMap.get(pos) >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void addPosition(Map<Integer, Map<String, Integer>> timeMap, int time, int r, int c) {
        timeMap.putIfAbsent(time, new HashMap<>());
        Map<String, Integer> posMap = timeMap.get(time);
        String posKey = r + "," + c;
        posMap.put(posKey, posMap.getOrDefault(posKey, 0) + 1);
    }
}
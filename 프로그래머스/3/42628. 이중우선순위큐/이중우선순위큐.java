import java.util.TreeMap;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                map.put(value, map.getOrDefault(value, 0) + 1);
            } else if (!map.isEmpty()) {
                int key = value == 1 ? map.lastKey() : map.firstKey();
                int count = map.get(key);

                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, count - 1);
                }
            }
        }

        if (map.isEmpty()) {
            return new int[]{0, 0};
        }

        answer = new int[]{map.lastKey(), map.firstKey()};
        return answer;
    }
}
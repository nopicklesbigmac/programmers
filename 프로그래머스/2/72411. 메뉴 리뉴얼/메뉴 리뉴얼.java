import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Solution {
    Map<String, Integer>[] courseMaps;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        courseMaps = new HashMap[11];
        for (int i = 0; i < 11; i++) {
            courseMaps[i] = new HashMap<>();
        }

        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            comb(arr, 0, new StringBuilder());
        }

        List<String> result = new ArrayList<>();

        for (int len : course) {
            Map<String, Integer> map = courseMaps[len];
            if (map.isEmpty()) continue;

            int maxFreq = 0;
            for (int freq : map.values()) {
                if (freq > maxFreq) {
                    maxFreq = freq;
                }
            }

            if (maxFreq < 2) continue;

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == maxFreq) {
                    result.add(entry.getKey());
                }
            }
        }

        Collections.sort(result);
        answer = result.toArray(new String[0]);
        return answer;
    }
private void comb(char[] arr, int idx, StringBuilder sb) {
        if (sb.length() >= 2) {
            int len = sb.length();
            String s = sb.toString();
            courseMaps[len].put(s, courseMaps[len].getOrDefault(s, 0) + 1);
        }

        for (int i = idx; i < arr.length; i++) {
            sb.append(arr[i]);
            comb(arr, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
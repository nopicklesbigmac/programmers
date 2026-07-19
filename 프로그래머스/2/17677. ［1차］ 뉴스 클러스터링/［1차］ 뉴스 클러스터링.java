import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        List<String> list1 = getList(str1);
        List<String> list2 = getList(str2);

        Map<String, Integer> map1 = getMap(list1);
        Map<String, Integer> map2 = getMap(list2);

        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        int intersection = 0;
        int union = 0;

        for (String key : keys) {
            int n1 = map1.getOrDefault(key, 0);
            int n2 = map2.getOrDefault(key, 0);
            intersection += Math.min(n1, n2);
            union += Math.max(n1, n2);
        }

        if (union == 0) return 65536;
        
        answer = (int) ((double) intersection / union * 65536);
        return answer;
    }
private List<String> getList(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2).toLowerCase();
            if (sub.charAt(0) >= 'a' && sub.charAt(0) <= 'z' && 
                sub.charAt(1) >= 'a' && sub.charAt(1) <= 'z') {
                list.add(sub);
            }
        }
        return list;
    }

    private Map<String, Integer> getMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
    }
}
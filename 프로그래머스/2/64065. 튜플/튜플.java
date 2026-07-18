import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2, s.length() - 2);
        String[] sets = s.split("\\},\\{");
        
        Arrays.sort(sets, (a, b) -> Integer.compare(a.length(), b.length()));
        
        List<Integer> result = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        
        for (String set : sets) {
            String[] elements = set.split(",");
            for (String element : elements) {
                int num = Integer.parseInt(element);
                if (added.add(num)) {
                    result.add(num);
                }
            }
        }
        
        answer = result.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}
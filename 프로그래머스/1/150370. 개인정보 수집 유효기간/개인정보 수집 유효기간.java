import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        long todayDays = dateToDays(today);
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] split = term.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]));
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            long collectedDays = dateToDays(split[0]);
            int termMonths = termMap.get(split[1]);
            if (collectedDays + (termMonths * 28) <= todayDays) {
                result.add(i + 1);
            }
        }
        
        
        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    private long dateToDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        return (year * 12 * 28) + (month * 28) + day;
    }
}
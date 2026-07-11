import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
answer = new int[id_list.length];
        
        Map<String, Set<String>> userReportMap = new HashMap<>();
        Map<String, Integer> reportedCountMap = new HashMap<>();
        
        for (String id : id_list) {
            userReportMap.put(id, new HashSet<>());
            reportedCountMap.put(id, 0);
        }
        for (String r : report) {
            String[] split = r.split(" ");
            String reporter = split[0];
            String target = split[1];
            if (userReportMap.get(reporter).add(target)) {
                reportedCountMap.put(target, reportedCountMap.get(target) + 1);
            }
        }
        for (int i = 0; i < id_list.length; i++) {
            String reporter = id_list[i];
            Set<String> targets = userReportMap.get(reporter);
            
            for (String target : targets) {
                if (reportedCountMap.get(target) >= k) {
                    answer[i]++;
                }
            }
        }
        
        return answer;
    }
}
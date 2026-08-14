import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
         Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).offer(ticket[1]);
        }

        List<String> route = new ArrayList<>();
        dfs("ICN", graph, route);

        Collections.reverse(route);

        answer = route.toArray(new String[0]);
        return answer;
    }
    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, List<String> route) {
        PriorityQueue<String> pq = graph.get(airport);

        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), graph, route);
        }

        route.add(airport);
    }
}
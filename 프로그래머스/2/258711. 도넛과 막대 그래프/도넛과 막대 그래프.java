import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {};
        
    Map<Integer, int[]> countMap = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            countMap.putIfAbsent(u, new int[2]);
            countMap.putIfAbsent(v, new int[2]);

            countMap.get(u)[0]++;
            countMap.get(v)[1]++;
        }

        int createdNode = -1;
        int donutCount = 0;
        int stickCount = 0;
        int eightCount = 0;

        for (Map.Entry<Integer, int[]> entry : countMap.entrySet()) {
            int node = entry.getKey();
            int outDegree = entry.getValue()[0];
            int inDegree = entry.getValue()[1];

            if (outDegree >= 2 && inDegree == 0) {
                createdNode = node;
                break;
            }
        }

        int totalGraphs = countMap.get(createdNode)[0];

        for (Map.Entry<Integer, int[]> entry : countMap.entrySet()) {
            int node = entry.getKey();
            if (node == createdNode) continue;

            int outDegree = entry.getValue()[0];
            int inDegree = entry.getValue()[1];

            if (outDegree == 0) {
                stickCount++;
            } else if (outDegree == 2 && inDegree >= 2) {
                eightCount++;
            }
        }

        donutCount = totalGraphs - stickCount - eightCount;

        answer = new int[]{createdNode, donutCount, stickCount, eightCount};
        return answer;
    }
}
import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
       if (x == y) {
            return answer;
        }
        
        boolean[] visited = new boolean[y + 1];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{x, 0});
        visited[x] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currVal = current[0];
            int count = current[1];
            
            int[] nextValues = {currVal + n, currVal * 2, currVal * 3};
            
            for (int nextVal : nextValues) {
                if (nextVal == y) {
                    answer = count + 1;
                    return answer;
                }
                
                if (nextVal < y && !visited[nextVal]) {
                    visited[nextVal] = true;
                    queue.offer(new int[]{nextVal, count + 1});
                }
            }
        }
        
        answer = -1;
        return answer;
    }
}
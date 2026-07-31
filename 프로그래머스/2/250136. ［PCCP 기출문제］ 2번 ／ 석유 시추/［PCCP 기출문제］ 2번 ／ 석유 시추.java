import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        
        int[][] group = new int[n][m];
        Map<Integer, Integer> groupSize = new HashMap<>();
        int groupId = 1;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && group[i][j] == 0) {
                    int size = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    group[i][j] = groupId;
                    size++;
                    
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            
                            if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                                if (land[nr][nc] == 1 && group[nr][nc] == 0) {
                                    group[nr][nc] = groupId;
                                    queue.add(new int[]{nr, nc});
                                    size++;
                                }
                            }
                        }
                    }
                    groupSize.put(groupId, size);
                    groupId++;
                }
            }
        }
        
        int maxOil = 0;
        
        for (int col = 0; col < m; col++) {
            Set<Integer> currentGroups = new HashSet<>();
            for (int row = 0; row < n; row++) {
                if (group[row][col] != 0) {
                    currentGroups.add(group[row][col]);
                }
            }
            
            int totalOil = 0;
            for (int id : currentGroups) {
                totalOil += groupSize.get(id);
            }
            
            answer = Math.max(answer, totalOil);
        }
        
        return answer;
    }
}
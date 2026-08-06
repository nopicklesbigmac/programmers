import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    int areaSize = 0;
                    
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    int targetColor = picture[i][j];

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        areaSize++;

                        for (int k = 0; k < 4; k++) {
                            int nx = current[0] + dx[k];
                            int ny = current[1] + dy[k];

                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if (picture[nx][ny] == targetColor && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    queue.add(new int[]{nx, ny});
                                }
                            }
                        }
                    }

                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, areaSize);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
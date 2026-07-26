import java.util.*;
class Solution {
    public int[] solution(String[] maps) {
        int[] answer = {};
        int rows = maps.length;
        int cols = maps[0].length();
        boolean[][] visited = new boolean[rows][cols];
        List<Integer> islandList = new ArrayList<>();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    int sum = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];
                        sum += maps[r].charAt(c) - '0';

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                                if (maps[nr].charAt(nc) != 'X' && !visited[nr][nc]) {
                                    visited[nr][nc] = true;
                                    queue.add(new int[]{nr, nc});
                                }
                            }
                        }
                    }
                    islandList.add(sum);
                }
            }
        }

        if (islandList.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(islandList);
        answer = new int[islandList.size()];
        for (int i = 0; i < islandList.size(); i++) {
            answer[i] = islandList.get(i);
        }

        return answer;
    }
}
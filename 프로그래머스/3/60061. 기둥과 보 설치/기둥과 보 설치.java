import java.util.*;
class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        boolean[][][] map = new boolean[n + 1][n + 1][2];

        for (int[] cmd : build_frame) {
            int x = cmd[0];
            int y = cmd[1];
            int a = cmd[2];
            int b = cmd[3];

            if (b == 1) {
                map[x][y][a] = true;

                if (!canBuild(map, x, y, a)) {
                    map[x][y][a] = false;
                }
            } else {
                map[x][y][a] = false;

                if (!isValid(map, n)) {
                    map[x][y][a] = true;
                }
            }
        }

        List<int[]> result = new ArrayList<>();

        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (map[x][y][0]) {
                    result.add(new int[]{x, y, 0});
                }
                if (map[x][y][1]) {
                    result.add(new int[]{x, y, 1});
                }
            }
        }

        answer = result.toArray(new int[0][]);
        return answer;
    }

    private boolean canBuild(boolean[][][] map, int x, int y, int type) {
        if (type == 0) {
            if (y == 0) return true;
            if (map[x][y - 1][0]) return true;
            if (x > 0 && map[x - 1][y][1]) return true;
            if (map[x][y][1]) return true;
        } else {
            if (y > 0 && map[x][y - 1][0]) return true;
            if (x + 1 < map.length && y > 0 && map[x + 1][y - 1][0]) return true;
            if (x > 0 && map[x - 1][y][1] && x + 1 < map.length && map[x + 1][y][1]) return true;
        }

        return false;
    }

    private boolean isValid(boolean[][][] map, int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (map[x][y][0] && !canBuild(map, x, y, 0)) {
                    return false;
                }

                if (map[x][y][1] && !canBuild(map, x, y, 1)) {
                    return false;
                }
            }
        }

        return true;
    }
}
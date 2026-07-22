import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        while (true) {
            Set<String> set = new HashSet<>();

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char target = map[i][j];
                    if (target == '.') {
                        continue;
                    }

                    if (map[i][j + 1] == target && map[i + 1][j] == target && map[i + 1][j + 1] == target) {
                        set.add(i + "," + j);
                        set.add(i + "," + (j + 1));
                        set.add((i + 1) + "," + j);
                        set.add((i + 1) + "," + (j + 1));
                    }
                }
            }

            if (set.isEmpty()) {
                break;
            }

            answer += set.size();

            for (String pos : set) {
                String[] rc = pos.split(",");
                int r = Integer.parseInt(rc[0]);
                int c = Integer.parseInt(rc[1]);
                map[r][c] = '.';
            }

            for (int c = 0; c < n; c++) {
                int idx = m - 1;
                for (int r = m - 1; r >= 0; r--) {
                    if (map[r][c] != '.') {
                        char temp = map[r][c];
                        map[r][c] = '.';
                        map[idx][c] = temp;
                        idx--;
                    }
                }
            }
        }

        return answer;
    }
}
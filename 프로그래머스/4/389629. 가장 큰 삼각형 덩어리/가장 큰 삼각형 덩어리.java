import java.util.*;

class Solution {
    int N, M;
    int[] group;
    int[] queue;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    int[][] dir = {
        {1, 2},
        {0, 2},
        {0, 3},
        {1, 3}
    };

    int[][] updownState = {
        {1, 0},
        {0, 1},
        {0, 1},
        {1, 0}
    };

    public int solution(int[][] grid) {
        N = grid.length;
        M = grid[0].length;

        int size = N * M * 2;
        group = new int[size];
        queue = new int[size];

        int answer = 0;
        int groupNum = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 2; k++) {
                    int id = (i * M + j) * 2 + k;

                    if (group[id] == 0) {
                        answer = Math.max(answer, bfs(i, j, k, groupNum, grid));
                        groupNum++;
                    }
                }
            }
        }

        return answer;
    }

    int bfs(int x, int y, int state, int groupNum, int[][] grid) {
        int head = 0;
        int tail = 0;
        int count = 0;

        int start = (x * M + y) * 2 + state;
        queue[tail++] = start;
        group[start] = groupNum;

        while (head < tail) {
            int cur = queue[head++];

            int pos = cur / 2;
            int currentState = cur % 2;

            int cx = pos / M;
            int cy = pos % M;

            count++;

            int shape;

            if (grid[cx][cy] == -1) {
                shape = currentState == 0 ? 0 : 2;
            } else {
                shape = currentState == 0 ? 1 : 3;
            }

            for (int k = 0; k < 2; k++) {
                int nd = dir[shape][k];

                int nx = cx + dx[nd];
                int ny = cy + dy[nd];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                int nextState;

                if (nd == 0 || nd == 1) {
                    nextState = updownState[shape][grid[nx][ny] == -1 ? 0 : 1];
                } else {
                    nextState = nd == 2 ? 1 : 0;
                }

                int nextId = (nx * M + ny) * 2 + nextState;
                int otherId = (nx * M + ny) * 2 + (1 - nextState);

                if (group[nextId] == groupNum || group[otherId] == groupNum) {
                    continue;
                }

                group[nextId] = groupNum;
                queue[tail++] = nextId;
            }
        }

        return count;
    }
}
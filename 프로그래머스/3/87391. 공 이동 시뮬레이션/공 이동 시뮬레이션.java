class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minX = x;
        long maxX = x;
        long minY = y;
        long maxY = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            long dx = queries[i][1];

            if (command == 0) {
                if (minY != 0) {
                    minY += dx;
                    if (minY >= m) return 0;
                }

                maxY = Math.min((long) m - 1, maxY + dx);

            } else if (command == 1) {
                if (maxY != m - 1) {
                    maxY -= dx;
                    if (maxY < 0) return 0;
                }

                minY = Math.max(0L, minY - dx);

            } else if (command == 2) {
                if (minX != 0) {
                    minX += dx;
                    if (minX >= n) return 0;
                }

                maxX = Math.min((long) n - 1, maxX + dx);

            } else {
                if (maxX != n - 1) {
                    maxX -= dx;
                    if (maxX < 0) return 0;
                }

                minX = Math.max(0L, minX - dx);
            }
        }

        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}
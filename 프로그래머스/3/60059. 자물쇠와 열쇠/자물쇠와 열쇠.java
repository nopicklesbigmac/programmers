class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        for (int r = 0; r < 4; r++) {
            for (int x = -m + 1; x < n; x++) {
                for (int y = -m + 1; y < n; y++) {
                    boolean possible = true;

                    for (int i = 0; i < n && possible; i++) {
                        for (int j = 0; j < n; j++) {
                            int keyValue = 0;

                            int ki = i - x;
                            int kj = j - y;

                            if (ki >= 0 && ki < m && kj >= 0 && kj < m) {
                                keyValue = key[ki][kj];
                            }

                            if (lock[i][j] + keyValue != 1) {
                                possible = false;
                                break;
                            }
                        }
                    }

                    if (possible) {
                        return true;
                    }
                }
            }

            key = rotate(key);
        }

        return false;
    }

    private int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = key[i][j];
            }
        }

        return result;
    }
}
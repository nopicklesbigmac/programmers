class Solution {
    public int solution(int[][] visible, int[][] hidden, int k) {
        int n = visible.length;
        int m = visible[0].length;
        int answer = 0;
        int limit = 1 << n;
        for (int mask = 0; mask < limit; mask++) {
            int cost = Integer.bitCount(mask) * k;
            int[] normal = new int[m];
            int[] reverse = new int[m];
            for (int j = 0; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        normal[j] += hidden[i][j];
                        reverse[j] += visible[i][j];
                    } else {
                        normal[j] += visible[i][j];
                        reverse[j] += hidden[i][j];
                    }
                }
            }
            int total = -cost;
            for (int j = 0; j < m; j++) {
                total += Math.max(normal[j], reverse[j] - k);
            }
            if (n % 2 == 1 || m % 2 == 1) {
                answer = Math.max(answer, total);
            } else {
                for (int j = 0; j < m; j++) {
                    int minNormal = Integer.MAX_VALUE;
                    int minReverse = Integer.MAX_VALUE;
                    for (int i = 0; i < n; i++) {
                        if ((i + j) % 2 == 1) {
                            if ((mask & (1 << i)) != 0) {
                                minNormal = Math.min(minNormal, hidden[i][j]);
                                minReverse = Math.min(minReverse, visible[i][j]);
                            } else {
                                minNormal = Math.min(minNormal, visible[i][j]);
                                minReverse = Math.min(minReverse, hidden[i][j]);
                            }
                        }
                    }
                    int withoutNormal = total - Math.max(normal[j], reverse[j] - k);
                    withoutNormal += Math.max(normal[j] - minNormal, reverse[j] - minReverse - k);
                    answer = Math.max(answer, withoutNormal);
                }
            }
        }
        return answer;
    }
}
class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int len = weak.length;
        int[] extended = new int[len * 2];

        for (int i = 0; i < len; i++) {
            extended[i] = weak[i];
            extended[i + len] = weak[i] + n;
        }

        boolean[] used = new boolean[dist.length];
        int[] selected = new int[dist.length];

        for (int count = 1; count <= dist.length; count++) {
            if (permutation(0, count, len, extended, dist, used, selected)) {
                return count;
            }
        }

        return -1;
    }

    private boolean permutation(int depth, int count, int len, int[] weak, int[] dist,
                                boolean[] used, int[] selected) {
        if (depth == count) {
            for (int start = 0; start < len; start++) {
                int index = start;

                for (int i = 0; i < count; i++) {
                    int limit = weak[index] + selected[i];
                    while (index < start + len && weak[index] <= limit) {
                        index++;
                    }

                    if (index >= start + len) {
                        return true;
                    }
                }
            }
            return false;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
                selected[depth] = dist[i];

                if (permutation(depth + 1, count, len, weak, dist, used, selected)) {
                    return true;
                }

                used[i] = false;
            }
        }

        return false;
    }
}
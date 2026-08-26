import java.util.*;
class Solution {
    public int[] solution(int[][] dice) {
        int[] answer = {};
        int n = dice.length;
        int half = n / 2;
        int maxWin = -1;
        answer = new int[half];

        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != half) continue;

            int[] a = new int[half];
            int[] b = new int[half];
            int ai = 0, bi = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    a[ai++] = i;
                } else {
                    b[bi++] = i;
                }
            }

            List<Integer> aSums = new ArrayList<>();
            List<Integer> bSums = new ArrayList<>();

            makeSums(dice, a, 0, 0, aSums);
            makeSums(dice, b, 0, 0, bSums);

            Collections.sort(bSums);

            int win = 0;

            for (int sum : aSums) {
                win += lowerBound(bSums, sum);
            }

            if (win > maxWin) {
                maxWin = win;

                for (int i = 0; i < half; i++) {
                    answer[i] = a[i] + 1;
                }
            }
        }

        return answer;
    }

    private void makeSums(int[][] dice, int[] selected, int depth, int sum, List<Integer> sums) {
        if (depth == selected.length) {
            sums.add(sum);
            return;
        }

        int die = selected[depth];

        for (int i = 0; i < 6; i++) {
            makeSums(dice, selected, depth + 1, sum + dice[die][i], sums);
        }
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
import java.util.*;
import java.util.function.Function;

class Solution {
    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> list = new ArrayList<>();

        for (int a = 1; a <= 9; a++)
            for (int b = 1; b <= 9; b++)
                for (int c = 1; c <= 9; c++)
                    for (int d = 1; d <= 9; d++)
                        if (a != b && a != c && a != d && b != c && b != d && c != d)
                            list.add(a * 1000 + b * 100 + c * 10 + d);

        int size = list.size();
        int[] nums = new int[size];
        int[] masks = new int[size];

        for (int i = 0; i < size; i++) {
            nums[i] = list.get(i);
            int x = nums[i];
            int mask = 0;
            mask |= 1 << (x / 1000);
            mask |= 1 << ((x / 100) % 10);
            mask |= 1 << ((x / 10) % 10);
            mask |= 1 << (x % 10);
            masks[i] = mask;
        }

        byte[][] score = new byte[size][size];

        for (int i = 0; i < size; i++) {
            int q = nums[i];
            int q1 = q / 1000;
            int q2 = (q / 100) % 10;
            int q3 = (q / 10) % 10;
            int q4 = q % 10;

            for (int j = 0; j < size; j++) {
                int v = nums[j];

                int s = 0;
                if (q1 == v / 1000) s++;
                if (q2 == (v / 100) % 10) s++;
                if (q3 == (v / 10) % 10) s++;
                if (q4 == v % 10) s++;

                int common = Integer.bitCount(masks[i] & masks[j]);
                int b = common - s;

                score[i][j] = (byte) (s * 5 + b);
            }
        }

        int[] candidates = new int[size];
        for (int i = 0; i < size; i++)
            candidates[i] = i;

        for (int turn = 0; turn < n; turn++) {
            if (candidates.length == 1)
                return nums[candidates[0]];

            int query = 0;
            int best = Integer.MAX_VALUE;

            for (int q = 0; q < size; q++) {
                int[] count = new int[21];

                for (int idx : candidates)
                    count[score[q][idx] & 255]++;

                int max = 0;
                for (int v : count)
                    max = Math.max(max, v);

                if (max < best) {
                    best = max;
                    query = q;
                }
            }

            String result = submit.apply(nums[query]);

            if ("4S 0B".equals(result))
                return nums[query];

            int s = result.charAt(0) - '0';
            int b = result.charAt(3) - '0';
            int target = s * 5 + b;

            int count = 0;
            for (int idx : candidates)
                if ((score[query][idx] & 255) == target)
                    count++;

            int[] next = new int[count];
            int p = 0;

            for (int idx : candidates)
                if ((score[query][idx] & 255) == target)
                    next[p++] = idx;

            candidates = next;
        }

        return nums[candidates[0]];
    }
}
import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    static class Count {
        long sum;
        int count;
        int start;

        Count(long sum, int count, int start) {
            this.sum = sum;
            this.count = count;
            this.start = start;
        }
    }

    static int add(int a, int b) {
        return (int) (((long) a + b) % MOD);
    }

    static int solve(int[] b) {
        List<Map<Long, Count>> dp = new ArrayList<>();

        for (int i = 0; i < b.length; i++) {
            Map<Long, Count> map = new HashMap<>();
            dp.add(map);

            long sum = b[i];

            if (i == 0) {
                map.put(sum, new Count(sum, 1, i));
            } else {
                int count = 0;

                for (Count value : dp.get(i - 1).values()) {
                    count = add(count, value.count);
                }

                map.put(sum, new Count(sum, count, i));
            }

            while (true) {
                long next = sum * 2;
                Count current = map.get(sum);

                if (current == null) {
                    break;
                }

                int prevIndex = current.start - 1;

                if (prevIndex < 0) {
                    break;
                }

                Map<Long, Count> prev = dp.get(prevIndex);

                if (!prev.containsKey(sum)) {
                    break;
                }

                Count prevCount = prev.get(sum);

                map.put(next, new Count(next, prevCount.count, prevCount.start));

                sum = next;
            }
        }

        int result = 0;

        for (Count value : dp.get(b.length - 1).values()) {
            result = add(result, value.count);
        }

        return result;
    }

    public int[] solution(int[] a, int[] s) {
        int[] answer = new int[s.length];
        int offset = 0;

        for (int i = 0; i < s.length; i++) {
            int[] b = Arrays.copyOfRange(a, offset, offset + s[i]);
            answer[i] = solve(b);
            offset += s[i];
        }

        return answer;
    }
}
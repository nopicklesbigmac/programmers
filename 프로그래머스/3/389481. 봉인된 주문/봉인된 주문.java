import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        Arrays.sort(bans);

        long[] ranks = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            ranks[i] = getRank(bans[i]);
        }

        Arrays.sort(ranks);

        long left = 1;
        long right = n + bans.length;

        while (left < right) {
            long mid = (left + right) / 2;
            long deleted = upperBound(ranks, mid);

            if (mid - deleted >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        answer = getString(left);
        return answer;
   }

    private long getRank(String s) {
        int len = s.length();
        long rank = 0;
        long power = 26;

        for (int i = 1; i < len; i++) {
            rank += power;
            power *= 26;
        }

        long value = 0;

        for (int i = 0; i < len; i++) {
            value = value * 26 + (s.charAt(i) - 'a');
        }

        return rank + value + 1;
    }

    private long upperBound(long[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private String getString(long rank) {
        int len = 1;
        long count = 26;

        while (rank > count) {
            rank -= count;
            len++;
            count *= 26;
        }

        rank--;

        char[] result = new char[len];

        for (int i = len - 1; i >= 0; i--) {
            result[i] = (char) ('a' + (rank % 26));
            rank /= 26;
        }

        return new String(result);
    }
}
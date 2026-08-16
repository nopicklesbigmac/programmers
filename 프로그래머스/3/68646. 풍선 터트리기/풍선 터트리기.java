class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int answer = 0;

        int[] right = new int[n];
        right[n - 1] = Integer.MAX_VALUE;

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(a[i + 1], right[i + 1]);
        }

        int left = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (a[i] < left || a[i] < right[i]) {
                answer++;
            }

            left = Math.min(left, a[i]);
        }

        return answer;
    }
}
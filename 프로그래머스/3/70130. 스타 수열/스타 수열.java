class Solution {
    public int solution(int[] a) {
        int answer = -1;
        int n = a.length;
        int[] count = new int[n];

        for (int num : a) {
            count[num]++;
        }

        answer = 0;

        for (int x = 0; x < n; x++) {
            if (count[x] * 2 <= answer) {
                continue;
            }

            int length = 0;
            int i = 0;

            while (i < n - 1) {
                if (a[i] != x && a[i + 1] != x && a[i] == a[i + 1]) {
                    i++;
                    continue;
                }

                if (a[i] == x && a[i] != a[i + 1]) {
                    length += 2;
                    i += 2;
                } else if (a[i + 1] == x && a[i] != a[i + 1]) {
                    length += 2;
                    i += 2;
                } else {
                    i++;
                }
            }

            answer = Math.max(answer, length);
        }

        return answer;
    }
}
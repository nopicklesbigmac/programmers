class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int[] state = new int[n + 1];

        int initial = n / 3;

        for (int i = 0; i < initial; i++) {
            state[cards[i]] = 1;
        }

        int idx = initial;
        answer = 1;

        while (idx < n) {
            int a = cards[idx++];
            int b = cards[idx++];

            state[a] = 2;
            state[b] = 2;

            int target = n + 1;

            if (findPair(state, n, target, 1, 1)) {
                removePair(state, n, target, 1, 1);
            } else if (coin >= 1 && findPair(state, n, target, 1, 2)) {
                removePair(state, n, target, 1, 2);
                coin--;
            } else if (coin >= 2 && findPair(state, n, target, 2, 2)) {
                removePair(state, n, target, 2, 2);
                coin -= 2;
            } else {
                break;
            }

            answer++;
        }

        return answer;
    }

    private boolean findPair(int[] state, int n, int target, int s1, int s2) {
        for (int x = 1; x <= n / 2; x++) {
            int y = target - x;

            if (y < 1 || y > n) {
                continue;
            }

            if (state[x] == s1 && state[y] == s2) {
                return true;
            }

            if (s1 != s2 && state[x] == s2 && state[y] == s1) {
                return true;
            }
        }

        return false;
    }

    private void removePair(int[] state, int n, int target, int s1, int s2) {
        for (int x = 1; x <= n / 2; x++) {
            int y = target - x;

            if (y < 1 || y > n) {
                continue;
            }

            if (state[x] == s1 && state[y] == s2) {
                state[x] = 0;
                state[y] = 0;
                return;
            }

            if (s1 != s2 && state[x] == s2 && state[y] == s1) {
                state[x] = 0;
                state[y] = 0;
                return;
            }
        }
    }
}
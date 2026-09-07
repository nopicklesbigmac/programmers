class Solution {
    public int solution(String[] words) {
        int answer = 0;

        int total = 0;

        for (String word : words) {
            total += word.length();
        }

        int[][] next = new int[total + 1][26];
        int[] count = new int[total + 1];

        int node = 1;

        for (String word : words) {
            int current = 0;

            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';

                if (next[current][c] == 0) {
                    next[current][c] = node++;
                }

                current = next[current][c];
                count[current]++;
            }
        }

        for (String word : words) {
            int current = 0;

            for (int i = 0; i < word.length(); i++) {
                current = next[current][word.charAt(i) - 'a'];

                if (count[current] == 1 || i == word.length() - 1) {
                    answer += i + 1;
                    break;
                }
            }
        }

        return answer;
    }
}
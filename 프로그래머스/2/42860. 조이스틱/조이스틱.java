class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        int move = n - 1;

        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            move = Math.min(move, i * 2 + n - next);
            move = Math.min(move, (n - next) * 2 + i);
        }

        answer += move;
        return answer;
    }
}
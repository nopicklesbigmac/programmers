class Solution {
    public int solution(int balls, int share) {
        int answer = 0;
        answer = solution2(balls, share);
        return answer;
    }
    private int solution2(int x, int y) {
        if (y == 0 || x == y) {
            return 1;
        }
        return solution2(x - 1, y - 1) + solution2(x - 1, y);
    }
}
class Solution {
    public int solution(int[] common) {
        int answer = 0;
        int len = common.length;
        if ((common[1] - common[0]) == (common[2] - common[1])) {
            int d = common[1] - common[0];
            answer = common[len - 1] + d;
        } else {
            int r = common[1] / common[0];
            answer = common[len - 1] * r;
        }
        return answer;
    }
}
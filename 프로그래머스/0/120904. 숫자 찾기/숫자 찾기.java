class Solution {
    public int solution(int num, int k) {
        int answer = 0;
        String numStr = String.valueOf(num);
        String kStr = String.valueOf(k);
        int index = numStr.indexOf(kStr);
        if (index == -1) {
            answer = -1;
        } else {
            answer = index + 1;
        }
        return answer;
    }
}
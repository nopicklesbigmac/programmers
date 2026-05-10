class Solution {
    public int solution(int[] array) {
        int answer = 0;
        for (int num : array) {
            int temp = num;
            while (temp > 0) {
                if (temp % 10 == 7) {
                    answer++;
                }
                temp /= 10;
            }
        }
        return answer;
    }
}
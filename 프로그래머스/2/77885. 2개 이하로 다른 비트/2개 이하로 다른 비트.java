class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = {};
        answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            if (x % 2 == 0) {
                answer[i] = x + 1;
            } else {
                long lowestZeroBit = (x + 1) & ~x;
                answer[i] = x + (lowestZeroBit >> 1);
            }
        }

        return answer;
    }
}
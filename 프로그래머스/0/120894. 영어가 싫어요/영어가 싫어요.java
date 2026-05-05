class Solution {
    public long solution(String numbers) {
        long answer = 0;
        String[] numStr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for (int i = 0; i < numStr.length; i++) {
            numbers = numbers.replace(numStr[i], String.valueOf(i));
        }
        answer = Long.parseLong(numbers);
        return answer;
    }
}
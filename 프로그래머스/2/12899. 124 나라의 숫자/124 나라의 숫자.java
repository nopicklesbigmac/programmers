class Solution {
    public String solution(int n) {
        String answer = "";
        String[] numbers = {"4", "1", "2"};
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            int remainder = n % 3;
            n /= 3;
            
            if (remainder == 0) {
                n -= 1;
            }
            
            sb.append(numbers[remainder]);
        }

        answer = sb.reverse().toString();
        return answer;
    }
}
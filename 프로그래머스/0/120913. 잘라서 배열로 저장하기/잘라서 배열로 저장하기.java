class Solution {
    public String[] solution(String my_str, int n) {
        String[] answer = {};
        int len = my_str.length();
        int arraySize = (len % n == 0) ? (len / n) : (len / n + 1);
        answer = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int start = i * n;
            int end = Math.min(start + n, len);
            
            answer[i] = my_str.substring(start, end);
        }
        return answer;
    }
}
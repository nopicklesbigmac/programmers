class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] weights = {781, 156, 31, 6, 1};
        String chars = "AEIOU";

        for (int i = 0; i < word.length(); i++) {
            int index = chars.indexOf(word.charAt(i));
            answer += 1 + index * weights[i];
        }

        return answer;
    }
}
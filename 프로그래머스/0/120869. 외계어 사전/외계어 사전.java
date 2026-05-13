class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 0;
        answer = 2;
        for (String word : dic) {
            boolean isMatch = true;
            for (String s : spell) {
                if (!word.contains(s)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch && word.length() == spell.length) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
}
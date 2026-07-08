class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int[] scores = new int[3];
        int dartIdx = -1;
        int i = 0;

        while (i < dartResult.length()) {
            char ch = dartResult.charAt(i);

            if (Character.isDigit(ch)) {
                dartIdx++;
                if (ch == '1' && i + 1 < dartResult.length() && dartResult.charAt(i + 1) == '0') {
                    scores[dartIdx] = 10;
                    i++;
                } else {
                    scores[dartIdx] = ch - '0';
                }
            } else if (ch == 'S' || ch == 'D' || ch == 'T') {
                if (ch == 'D') scores[dartIdx] = (int) Math.pow(scores[dartIdx], 2);
                else if (ch == 'T') scores[dartIdx] = (int) Math.pow(scores[dartIdx], 3);
            } else if (ch == '*') {
                scores[dartIdx] *= 2;
                if (dartIdx > 0) scores[dartIdx - 1] *= 2;
            } else if (ch == '#') {
                scores[dartIdx] *= -1;
            }
            i++;
        }

        answer = scores[0] + scores[1] + scores[2];
        return answer;
    }
}
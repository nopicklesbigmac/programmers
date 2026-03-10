class Solution {
    public String solution(int[] numLog) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < numLog.length; i++) {
            int diff = numLog[i] - numLog[i - 1];
            switch (diff) {
                case 1:   sb.append("w"); break;
                case -1:  sb.append("s"); break;
                case 10:  sb.append("d"); break;
                case -10: sb.append("a"); break;
            }
        }
        answer = sb.toString();
        
        return answer;
    }
}
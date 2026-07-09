class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        int[] xCounts = new int[10];
        int[] yCounts = new int[10];

        for (char c : X.toCharArray()) {
            xCounts[c - '0']++;
        }
        for (char c : Y.toCharArray()) {
            yCounts[c - '0']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            int count = Math.min(xCounts[i], yCounts[i]);
            for (int j = 0; j < count; j++) {
                sb.append(i);
            }
        }

        if (sb.length() == 0) {
            return "-1";
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }

        answer = sb.toString();
        return answer;
    }
}
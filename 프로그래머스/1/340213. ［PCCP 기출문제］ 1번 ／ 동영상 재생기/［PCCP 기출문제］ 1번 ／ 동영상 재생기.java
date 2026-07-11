class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int vLen = timeToSec(video_len);
        int cur = timeToSec(pos);
        int opS = timeToSec(op_start);
        int opE = timeToSec(op_end);

        cur = checkOpening(cur, opS, opE);

        for (String cmd : commands) {
            if (cmd.equals("prev")) {
                cur = Math.max(0, cur - 10);
            } else if (cmd.equals("next")) {
                cur = Math.min(vLen, cur + 10);
            }
            cur = checkOpening(cur, opS, opE);
        }

        answer = String.format("%02d:%02d", cur / 60, cur % 60);
        return answer;
    }

    private int timeToSec(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    private int checkOpening(int cur, int opS, int opE) {
        if (cur >= opS && cur <= opE) {
            return opE;
        }
        return cur;
    }
}
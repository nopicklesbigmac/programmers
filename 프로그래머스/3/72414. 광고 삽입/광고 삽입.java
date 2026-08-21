class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int play = toSecond(play_time);
        int adv = toSecond(adv_time);

        long[] timeline = new long[play + 1];

        for (String log : logs) {
            int start = toSecond(log.substring(0, 8));
            int end = toSecond(log.substring(9));

            timeline[start]++;
            timeline[end]--;
        }

        for (int i = 1; i <= play; i++) {
            timeline[i] += timeline[i - 1];
        }

        long current = 0;

        for (int i = 0; i < adv; i++) {
            current += timeline[i];
        }

        long max = current;
        int answer_i = 0;

        for (int start = 1; start + adv <= play; start++) {
            current -= timeline[start - 1];
            current += timeline[start + adv - 1];

            if (current > max) {
                max = current;
                answer_i = start;
            }
        }

        answer = toTime(answer_i);
        return answer;
     }

    private int toSecond(String time) {
        String[] parts = time.split(":");

        return Integer.parseInt(parts[0]) * 3600
                + Integer.parseInt(parts[1]) * 60
                + Integer.parseInt(parts[2]);
    }

    private String toTime(int time) {
        int hour = time / 3600;
        time %= 3600;

        int minute = time / 60;
        int second = time % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
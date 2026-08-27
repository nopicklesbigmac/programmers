class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int n = lines.length;
        int[] end = new int[n];
        int[] start = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = lines[i].split(" ");
            end[i] = toMillis(parts[1]);
            
            String t = parts[2].substring(0, parts[2].length() - 1);
            double duration = Double.parseDouble(t);
            int durationMs = (int) Math.round(duration * 1000);

            start[i] = end[i] - durationMs + 1;
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            int windowStart = end[i];
            int windowEnd = end[i] + 999;

            for (int j = 0; j < n; j++) {
                if (start[j] <= windowEnd && end[j] >= windowStart) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }
        return answer;
    }
 private int toMillis(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        String[] second = parts[2].split("\\.");
        int sec = Integer.parseInt(second[0]);
        int ms = Integer.parseInt(second[1]);

        return ((hour * 60 + minute) * 60 + sec) * 1000 + ms;
    }
}

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] crew = new int[timetable.length];

        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            crew[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }

        java.util.Arrays.sort(crew);

        int index = 0;
        int last = 0;

        for (int i = 0; i < n; i++) {
            int busTime = 9 * 60 + i * t;
            int count = 0;
            int lastCrew = 0;

            while (index < crew.length && crew[index] <= busTime && count < m) {
                lastCrew = crew[index++];
                count++;
            }

            if (i == n - 1) {
                if (count < m) {
                    last = busTime;
                } else {
                    last = lastCrew - 1;
                }
            }
        }

        answer = String.format("%02d:%02d", last / 60, last % 60);
        return answer;
    }
}
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;

        for (int i = 0; i < n; i++) {
            int scheduleTime = schedules[i];
            int targetMinutes = (scheduleTime / 100) * 60 + (scheduleTime % 100);
            int deadline = targetMinutes + 10;
            
            boolean isEligible = true;
            for (int j = 0; j < 7; j++) {
                int currentDay = (startday + j - 1) % 7;
                if (currentDay == 5 || currentDay == 6) continue;
                
                int log = timelogs[i][j];
                int logMinutes = (log / 100) * 60 + (log % 100);
                if (logMinutes > deadline) {
                    isEligible = false;
                    break;
                }
            }
            
            if (isEligible) {
                answer++;
            }
        }
        
        return answer;
    }
}
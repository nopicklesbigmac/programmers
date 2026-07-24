class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] activeServers = new int[24];

        for (int i = 0; i < 24; i++) {
            int currentServers = 0;
            for (int j = Math.max(0, i - k + 1); j <= i; j++) {
                currentServers += activeServers[j];
            }

            int requiredServers = players[i] / m;

            if (currentServers < requiredServers) {
                int add = requiredServers - currentServers;
                activeServers[i] = add;
                answer += add;
            }
        }

        return answer;
    }
}
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i, n, computers, visited);
            }
        }

        return answer;
    }

    private void dfs(int computer, int n, int[][] computers, boolean[] visited) {
        visited[computer] = true;

        for (int i = 0; i < n; i++) {
            if (computers[computer][i] == 1 && !visited[i]) {
                dfs(i, n, computers, visited);
            }
        }
    }
}
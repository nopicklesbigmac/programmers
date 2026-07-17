class Solution {
    private int maxCount = 0;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, 0);
        
        answer = maxCount;
        return answer;
    }

    private void dfs(int k, int[][] dungeons, boolean[] visited, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, count + 1);
                visited[i] = false;
            }
        }
        
        if (count > maxCount) {
            maxCount = count;
        }
    }
}
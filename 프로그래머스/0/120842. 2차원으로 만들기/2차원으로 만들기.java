class Solution {
    public int[][] solution(int[] num_list, int n) {
        int[][] answer = {};
        int rowCount = num_list.length / n;
        answer = new int[rowCount][n];

        for (int i = 0; i < num_list.length; i++) {
            answer[i / n][i % n] = num_list[i];
        }
        return answer;
    }
}
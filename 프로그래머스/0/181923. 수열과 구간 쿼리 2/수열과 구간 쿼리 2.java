class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = findMinAboveK(arr, queries[i][0], queries[i][1], queries[i][2]);
        }
        return answer;
    }
    private int findMinAboveK(int[] arr, int s, int e, int k) {
        int min = 1000001;
        for (int i = s; i <= e; i++) {
            if (arr[i] > k && arr[i] < min) {
                min = arr[i];
            }
        }
        return (min == 1000001) ? -1 : min;
    }
}
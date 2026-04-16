class Solution {
    public int solution(int[][] arr) {
        int answer = 0;
        int n = arr.length;
        boolean isSymmetric = true;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != arr[j][i]) {
                    isSymmetric = false;
                    break;
                }
            }
            if (!isSymmetric) break;
        }
        if (isSymmetric) {
            answer = 1;
        } else {
            answer = 0;
        }
        return answer;
    }
}   
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        int[][] matrix = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = num++;
            }
        }
        answer = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            int x1 = queries[k][0];
            int y1 = queries[k][1];
            int x2 = queries[k][2];
            int y2 = queries[k][3];

            int temp = matrix[x1][y1];
            int min = temp;

            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                min = Math.min(min, matrix[i][y1]);
            }

            for (int j = y1; j < y2; j++) {
                matrix[x2][j] = matrix[x2][j + 1];
                min = Math.min(min, matrix[x2][j]);
            }

            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                min = Math.min(min, matrix[i][y2]);
            }

            for (int j = y2; j > y1 + 1; j--) {
                matrix[x1][j] = matrix[x1][j - 1];
                min = Math.min(min, matrix[x1][j]);
            }

            matrix[x1][y1 + 1] = temp;

            answer[k] = min;
        }

        return answer;
    }
}
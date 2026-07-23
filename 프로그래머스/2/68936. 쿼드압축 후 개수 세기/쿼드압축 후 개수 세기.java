class Solution {
    private int[] answer = new int[2];
    public int[] solution(int[][] arr) {
        quadTree(arr, 0, 0, arr.length);
        return answer;
    }

    private void quadTree(int[][] arr, int x, int y, int size) {
        if (isSame(arr, x, y, size)) {
            answer[arr[x][y]]++;
            return;
        }

        int newSize = size / 2;
        quadTree(arr, x, y, newSize);
        quadTree(arr, x + newSize, y, newSize);
        quadTree(arr, x, y + newSize, newSize);
        quadTree(arr, x + newSize, y + newSize, newSize);
    }

    private boolean isSame(int[][] arr, int x, int y, int size) {
        int start = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != start) {
                    return false;
                }
            }
        }
        return true;
    }
}
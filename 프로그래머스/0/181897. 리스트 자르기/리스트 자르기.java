class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        int[] answer = {};
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];
        
        int start = 0;
        int end = 0;
        int step = 1;
        if (n == 1) {
            start = 0;
            end = b;
        } else if (n == 2) {
            start = a;
            end = num_list.length - 1;
        } else if (n == 3) {
            start = a;
            end = b;
        } else if (n == 4) {
            start = a;
            end = b;
            step = c;
        }
        int size = (end - start) / step + 1;
        answer = new int[size];
        for (int i = 0, j = start; j <= end; i++, j += step) {
            answer[i] = num_list[j];
        }
        return answer;
    }
}
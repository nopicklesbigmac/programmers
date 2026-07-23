class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int left = 0;
        int right = 0;
        int sum = 0;
        
        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;

        while (right < sequence.length) {
            sum += sequence[right];

            while (sum > k) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int length = right - left;
                if (length < minLength) {
                    minLength = length;
                    start = left;
                    end = right;
                }
            }
            
            right++;
        }

        answer = new int[] { start, end };
        return answer;
    }
}
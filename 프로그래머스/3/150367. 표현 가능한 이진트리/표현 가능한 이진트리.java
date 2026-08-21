class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = {};
        answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            int size = 1;
            while (size < binary.length()) {
                size = size * 2 + 1;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size - binary.length(); j++) {
                sb.append('0');
            }
            sb.append(binary);

            answer[i] = check(sb.toString(), 0, sb.length() - 1) ? 1 : 0;
        }

        return answer;
    }

    private boolean check(String binary, int left, int right) {
        if (left == right) {
            return true;
        }

        int mid = (left + right) / 2;

        if (binary.charAt(mid) == '0') {
            for (int i = left; i <= right; i++) {
                if (binary.charAt(i) == '1') {
                    return false;
                }
            }
            return true;
        }

        return check(binary, left, mid - 1)
                && check(binary, mid + 1, right);
    }
}
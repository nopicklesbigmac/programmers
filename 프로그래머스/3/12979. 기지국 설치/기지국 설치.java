class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 2 * w + 1;
        int start = 1;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        for (int station : stations) {
            int left = station - w;
            int right = station + w;

            if (start < left) {
                int gap = left - start;
                answer += (gap + range - 1) / range;
            }

            start = right + 1;
        }

        if (start <= n) {
            int gap = n - start + 1;
            answer += (gap + range - 1) / range;
        }

        return answer;
    }
}
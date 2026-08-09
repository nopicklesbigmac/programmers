class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        answer = (int)(countOnes(r) - countOnes(l - 1));
        return answer;
    }
    private long countOnes(long x) {
        if (x <= 0) return 0;
        
        long div = 1;
        while (div * 5 <= x) {
            div *= 5;
        }

        long ans = 0;
        while (div > 0) {
            long idx = x / div;
            if (idx == 2) {
                ans += idx * power4(div);
                break;
            } else if (idx > 2) {
                ans += (idx - 1) * power4(div);
                x %= div;
                div /= 5;
            } else {
                ans += idx * power4(div);
                x %= div;
                div /= 5;
            }
        }
        return ans;
    }

    private long power4(long div) {
        long p = 1;
        while (div > 1) {
            p *= 4;
            div /= 5;
        }
        return p;
    }
}
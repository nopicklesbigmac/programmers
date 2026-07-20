class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String converted = Integer.toString(n, k);
        String[] parts = converted.split("0");

        for (String part : parts) {
            if (part.isEmpty()) continue;
            
            long p = Long.parseLong(part);
            if (isPrime(p)) {
                answer++;
            }
        }
        
        return answer;
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        
        for (long i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
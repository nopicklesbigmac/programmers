import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> primeSet = new HashSet<>();
        
        for (int i = 1; i <= numbers.length(); i++) {
            generatePermutations("", numbers, i, primeSet);
        }
        
        for (int num : primeSet) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void generatePermutations(String current, String remain, int length, Set<Integer> primeSet) {
        if (current.length() == length) {
            primeSet.add(Integer.parseInt(current));
            return;
        }
        
        for (int i = 0; i < remain.length(); i++) {
            generatePermutations(
                current + remain.charAt(i),
                remain.substring(0, i) + remain.substring(i + 1),
                length,
                primeSet
            );
        }
    }
    
    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
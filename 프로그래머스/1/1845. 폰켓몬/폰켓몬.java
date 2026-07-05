import java.util.HashSet;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            set.add(num);
        }
        
        int limit = nums.length / 2;
        int uniqueCount = set.size();
        
        answer = Math.min(uniqueCount, limit);
        return answer;
    }
}
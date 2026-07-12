class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] nums = s.split(" ");
        
        int min = Integer.parseInt(nums[0]);
        int max = Integer.parseInt(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            int current = Integer.parseInt(nums[i]);
            min = Math.min(min, current);
            max = Math.max(max, current);
        }
        
        answer = min + " " + max;
        
        return answer;
    }
}
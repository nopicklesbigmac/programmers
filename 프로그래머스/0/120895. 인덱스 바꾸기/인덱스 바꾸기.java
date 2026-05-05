class Solution {
    public String solution(String my_string, int num1, int num2) {
        String answer = "";
        char[] chars = my_string.toCharArray();
        
        // 2. 두 인덱스의 값을 교환 (Swap)
        char temp = chars[num1];
        chars[num1] = chars[num2];
        chars[num2] = temp;
        
        // 3. 배열을 다시 문자열로 변환하여 반환
        answer = new String(chars);
        return answer;
    }
}
class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        String str1 = String.valueOf(a) + String.valueOf(b);
        int int1 = Integer.parseInt(str1);
        int int2 = 2 * a * b;
        answer =int1 >= int2 ? int1 : int2;
        return answer;
    }
}
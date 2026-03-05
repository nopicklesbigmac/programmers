class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        String strab = String.valueOf(a) + String.valueOf(b);
        String strba = String.valueOf(b) + String.valueOf(a);
        int ab = Integer.parseInt(strab);
        int ba = Integer.parseInt(strba);
        answer =ab >= ba ? ab : ba;
        return answer;
    }
}
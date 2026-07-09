class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int leftPos = 10; 
        int rightPos = 12;

        for (int num : numbers) {
            if (num == 0) num = 11; 
            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
                leftPos = num;
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
                rightPos = num;
            } else {
                int leftDist = getDistance(leftPos, num);
                int rightDist = getDistance(rightPos, num);

                if (leftDist < rightDist) {
                    sb.append("L");
                    leftPos = num;
                } else if (rightDist < leftDist) {
                    sb.append("R");
                    rightPos = num;
                } else {
                    if (hand.equals("left")) {
                        sb.append("L");
                        leftPos = num;
                    } else {
                        sb.append("R");
                        rightPos = num;
                    }
                }
            }
        }
        answer = sb.toString();
        return answer;
    }

    private int getDistance(int pos, int num) {
        int posR = (pos - 1) / 3;
        int posC = (pos - 1) % 3;
        int numR = (num - 1) / 3;
        int numC = (num - 1) % 3;
        
        return Math.abs(posR - numR) + Math.abs(posC - numC);
    }
}
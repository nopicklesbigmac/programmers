class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        int h = park.length;
        int w = park[0].length();
        int currR = 0;
        int currC = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (park[i].charAt(j) == 'S') {
                    currR = i;
                    currC = j;
                }
            }
        }

        for (String route : routes) {
            String[] parts = route.split(" ");
            char op = parts[0].charAt(0);
            int n = Integer.parseInt(parts[1]);

            int dr = 0;
            int dc = 0;

            if (op == 'N') dr = -1;
            else if (op == 'S') dr = 1;
            else if (op == 'W') dc = -1;
            else if (op == 'E') dc = 1;

            int tempR = currR;
            int tempC = currC;
            boolean possible = true;

            for (int i = 0; i < n; i++) {
                tempR += dr;
                tempC += dc;

                if (tempR < 0 || tempR >= h || tempC < 0 || tempC >= w || park[tempR].charAt(tempC) == 'X') {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                currR = tempR;
                currC = tempC;
            }
        }

        answer = new int[]{currR, currC};
        return answer;
    }
}
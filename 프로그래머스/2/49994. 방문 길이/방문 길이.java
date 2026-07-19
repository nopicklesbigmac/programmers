import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int x = 0;
        int y = 0;
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < dirs.length(); i++) {
            char d = dirs.charAt(i);
            int nx = x;
            int ny = y;

            if (d == 'U') ny++;
            else if (d == 'D') ny--;
            else if (d == 'R') nx++;
            else if (d == 'L') nx--;

            if (nx >= -5 && nx <= 5 && ny >= -5 && ny <= 5) {
                visited.add("" + x + y + nx + ny);
                visited.add("" + nx + ny + x + y);
                x = nx;
                y = ny;
            }
        }

        answer = visited.size() / 2;
        return answer;
    }
}
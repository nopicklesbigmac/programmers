import java.util.stream.Collectors;
import java.util.stream.Stream;
class Solution {
    public String solution(String s) {
        String answer = "";
        answer = Stream.of(s.split(""))
                     .sorted(java.util.Collections.reverseOrder())
                     .collect(Collectors.joining());
        return answer;
    }
}